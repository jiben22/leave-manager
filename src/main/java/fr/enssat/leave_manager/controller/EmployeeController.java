package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.*;
import fr.enssat.leave_manager.repository.PasswordResetTokenRepository;
import fr.enssat.leave_manager.service.EmailService;
import fr.enssat.leave_manager.service.EmployeeService;
import fr.enssat.leave_manager.service.TeamService;
import fr.enssat.leave_manager.service.exception.not_found.EmployeeNotFoundException;
import fr.enssat.leave_manager.service.exception.not_found.TeamNotFoundException;
import fr.enssat.leave_manager.service.impl.*;
import fr.enssat.leave_manager.utils.MailSender;
import fr.enssat.leave_manager.utils.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    private final TeamService teamService;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private HRServiceImpl hrService;
    @Autowired
    private HRDServiceImpl hrdService;
    @Autowired
    private TeamLeaderServiceImpl teamLeaderService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService, TeamServiceImpl teamService) {
        this.employeeService = employeeService;
        this.teamService = teamService;
    }

    @PreAuthorize("hasAnyRole('ROLE_HRD','ROLE_HR','ROLE_TEAMLEADER')")
    @GetMapping("/employes")
    public String showEmployees(Model model) {

        log.info("GET /employes");

        model.addAttribute("title", "Liste des employés");

        // Get employees
        List<EmployeeEntity> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);

        return "employees";
    }

    //@PreAuthorize("hasAnyRole('ROLE_HRD','ROLE_HR','ROLE_TEAMLEADER')")
    @GetMapping("/employe/{id}")
    public String showEmployeById(@PathVariable String id, Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        EmployeeEntity user = (EmployeeEntity) session.getAttribute("employee");

        if (user.getEid().equals(id) || request.isUserInRole("ROLE_TEAMLEADER") || request.isUserInRole("ROLE_HR")) {

            log.info("GET /employe/" + id);

            model.addAttribute("title", "Visualiser l'employé");

            // Get employe by id
            EmployeeEntity employee = employeeService.getEmployee(id);
            model.addAttribute("employee", employee);
            Collection<String> roles = employee.getRoles();
            if (roles.contains("ROLE_HRD")) {
                roles.remove("ROLE_HR");
                roles.remove("ROLE_TEAMLEADER");
            }
            model.addAttribute("employee_role", RoleEnum.valueOf(
                    String.valueOf(
                            Arrays.asList(roles.toArray()).get(0)
                    ).replace("ROLE_", "")).toString());

            return "showEmployee";
        }

        response.setStatus(403);
        return "error";
    }

    @PreAuthorize("hasRole('ROLE_HRD')")
    @GetMapping("/employe/ajouter")
    public String showAddEmployeeForm(Model model) {

        log.info("GET /employe/ajouter");

        model.addAttribute("title", "Ajouter un employé");
        model.addAttribute("employee", new EmployeeEntity());

        // Get teams
        List<TeamEntity> teams =  teamService.getTeams();
        model.addAttribute("teams", teams);

        model.addAttribute("roles", RoleEnum.values());

        return "addEmployeeForm";
    }

    @PreAuthorize("hasRole('ROLE_HRD')")
    @PostMapping("/employe/ajouter")
    public String submitAddEmployeeForm(@Valid @ModelAttribute("employee") EmployeeEntity employee,
                                        @RequestParam("role") String role,
                                        BindingResult result, Model model,
                                        RedirectAttributes redirectAttributes, HttpServletRequest request) {

        log.info("POST /employe/ajouter");

        // Get teams
        List<TeamEntity> teams =  teamService.getTeams();
        model.addAttribute("teams", teams);

        model.addAttribute("roles", RoleEnum.values());

        // Check if form has errors
        if (result.hasErrors()) {
            log.info(result.toString());

            // Return form with errors
            return "addEmployeeForm";
        }

        // Check if emails already exists
        if ( !employee.getEmail().isEmpty() ) {
            try {
                EmployeeEntity existedEmployee =
                        employeeService.getEmployeeByEmail(employee.getEmail());

                if ( existedEmployee != null ) {
                    result.rejectValue("email", "employee.email",
                            "L'email est déjà utilisé pour un autre employé");

                    // Return form with errors
                    return "addEmployeeForm";
                }
            } catch (EmployeeNotFoundException e) {

                log.info("Email doesn't exist: " + employee.getEmail());

                try {
                    // Save employee
                    employee = employeeService.addEmployee(employee);
                    if (role.equalsIgnoreCase(RoleEnum.HRD.toString())) {
                        hrService.addHR(HREntity.builder()
                                .eid(employee.getEid())
                                .employee(employee)
                                .build());
                        teamLeaderService.addTeamLeader(TeamLeaderEntity.builder()
                                .eid(employee.getEid())
                                .employee(employee)
                                .build());
                        hrdService.addHRD(HRDEntity.builder()
                                .eid(employee.getEid())
                                .employee(employee)
                                .build());
                    } else if (role.equalsIgnoreCase(RoleEnum.HR.toString())) {
                        hrService.addHR(HREntity.builder()
                                .eid(employee.getEid())
                                .employee(employee)
                                .build());
                    } else if (role.equalsIgnoreCase(RoleEnum.TEAMLEADER.toString())) {
                        teamLeaderService.addTeamLeader(TeamLeaderEntity.builder()
                                .eid(employee.getEid())
                                .employee(employee)
                                .build());
                    }

                    PasswordResetToken token = new PasswordResetToken();
                    token.setToken(UUID.randomUUID().toString());
                    token.setUser(employee);
                    token.setExpiryDate(30);
                    tokenRepository.save(token);

                    // send mail
                    Map <String, String> mailContent = new HashMap<>();
                    mailContent.put("recipient", employee.getEmail());
                    mailContent.put("firstname", employee.getFirstname());
                    mailContent.put("subject", "Bienvenue dans l'entreprise " + employee.getFirstname());
                    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
                    mailContent.put("resetUrl", url + "/resetPassword/new/?token=" + token.getToken());
                    mailContent.put("templateId", "1112887");
                    MailSender.sendMail(mailContent);

                } catch (Exception e1) {
                    log.error(e1.getMessage() + e1.getCause());
                    redirectAttributes.addFlashAttribute("message", "Impossible d'enregister l'employé");

                    return "redirect:/employes";
                }


            }

        }

        return "redirect:/employe/" + employee.getEid();
    }

    @PreAuthorize("hasRole('ROLE_HR')")
    @GetMapping("/employe/modifier/{id}")
    public String showUpdateEmployeeForm(@PathVariable String id, Model model) {

        log.info("GET /employe/modifier/" + id);

        model.addAttribute("title", "Modifier l'employé");

        // Get employee by id
        EmployeeEntity employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);
        Collection<String> roles = employee.getRoles();
        if (roles.contains("ROLE_HRD")) {
            roles.remove("ROLE_HR");
            roles.remove("ROLE_TEAMLEADER");
        }
        model.addAttribute("employee_role", RoleEnum.valueOf(
                String.valueOf(
                        Arrays.asList(roles.toArray()).get(0)
                ).replace("ROLE_", "")).toString());

        // Get teams
        List<TeamEntity> teams =  teamService.getTeams();
        model.addAttribute("teams", teams);

        model.addAttribute("roles", RoleEnum.values());

        return "updateEmployeeForm";
    }

    @PreAuthorize("hasRole('ROLE_HR')")
    @PostMapping("/employe/modifier/{id}")
    public String submitUpdateEmployeeForm(@PathVariable String id,
                                           @Valid @ModelAttribute("employee") EmployeeEntity employee,
                                           @RequestParam("role") String role,
                                           BindingResult result, Model model,
                                           RedirectAttributes redirectAttributes) {

        log.info("POST /employe/modifier/" + id);

        model.addAttribute("title", "Modifier l'employé");

        if (result.hasErrors()) {
            log.info(result.toString());

            // Get teams
            List<TeamEntity> teams =  teamService.getTeams();
            model.addAttribute("teams", teams);

            // Return form with errors
            return "updateEmployeeForm";
        }

        try {
            // Save employee
            if (role.equalsIgnoreCase(RoleEnum.HRD.toString())) {
                try{hrService.addHR(HREntity.builder()
                        .eid(employee.getEid())
                        .employee(employee)
                        .build());} catch (Exception e) {}
                try{teamLeaderService.addTeamLeader(TeamLeaderEntity.builder()
                        .eid(employee.getEid())
                        .employee(employee)
                        .build());} catch (Exception e) {}
                hrdService.addHRD(HRDEntity.builder()
                        .eid(employee.getEid())
                        .employee(employee)
                        .build());
            } else if (role.equalsIgnoreCase(RoleEnum.HR.toString())) {
                try{hrdService.deleteHRD(employee.getEid()); } catch (Exception e) {}
                try{teamLeaderService.deleteTeamLeader(employee.getEid()); } catch (Exception e) {}
                hrService.addHR(HREntity.builder()
                        .eid(employee.getEid())
                        .employee(employee)
                        .build());
            } else if (role.equalsIgnoreCase(RoleEnum.TEAMLEADER.toString())) {
                try {hrdService.deleteHRD(employee.getEid()); } catch (Exception e) {}
                try {hrService.deleteHR(employee.getEid()); } catch (Exception e) {}
                teamLeaderService.addTeamLeader(TeamLeaderEntity.builder()
                        .eid(employee.getEid())
                        .employee(employee)
                        .build());
            }
            employeeService.editEmployee(employee);
        } catch (Exception e) {
            log.error(e.getMessage() + e.getCause());
            redirectAttributes.addFlashAttribute("message", "Impossible de modifier l'employé.");

            return "redirect:/employes";
        }

        return "redirect:/employe/" + employee.getEid();
    }

    @GetMapping("/employe/ajouter/{teamId}")
    public String showAddEmployeeToTeamForm(@PathVariable String teamId, Model model,
                                            RedirectAttributes redirectAttributes) {

        log.info("GET /employe/ajouter/" + teamId);

        model.addAttribute("title", "Ajouter un employé dans l'équipe");

        TeamEntity team = null;
        // Get team
        try {
            team = teamService.getTeam(teamId);
            model.addAttribute("team", team);
        } catch (TeamNotFoundException e) {
            log.error("Employee not found: " + teamId);
            redirectAttributes.addFlashAttribute("message", "L'équipe n'a pas été trouvée");
            return "redirect:/equipes";
        }

        // Set team in the list
        List<TeamEntity> teams =  new ArrayList<>();
        teams.add(team);
        model.addAttribute("teams", teams);

        // Get employees
        List<EmployeeEntity> employees =  employeeService.getEmployees();
        model.addAttribute("employees", employees);

        // Set department to the list
        List<DepartmentEntity> departments = new ArrayList<>();
        departments.add(team.getDepartment());
        model.addAttribute("departments", departments);

        return "addTeamForm";
    }

    @PostMapping("/employe/ajouter/{teamId}")
    public String submitAddEmployeeToTeamForm(@PathVariable String teamId,
                                              @Valid @ModelAttribute("team") TeamEntity team,
                                              BindingResult result, Model model,
                                              RedirectAttributes redirectAttributes, HttpSession session) {

        log.info("POST /employe/ajouter/" + teamId);

        EmployeeEntity opt_emp = (EmployeeEntity) session.getAttribute("employee");
        EmployeeEntity emp = employeeService.getEmployeeByEmail(opt_emp.getEmail());

        model.addAttribute("title", "Ajouter un employé dans l'équipe");

        // Set team in the list
        System.err.println(team);
//        team = teamService.getTeam(teamId);
//        Set<EmployeeEntity> list = team.getEmployeeList();
//        list.add(emp);
//        team.setEmployeeList(list);
        List<TeamEntity> teams = new ArrayList<>();
//        teams.add(team);
        model.addAttribute("teams", teams);

        // Check if form has errors
        if (result.hasErrors()) {
            log.info(result.toString());

            // Return form with errors
            return "addEmployeeForm";
        }

        // Update team
        try {
            log.info(teamId);
            team = teamService.editTeam(team);
            log.info(team.getId());
        } catch (Exception e) {
            log.error(e.getMessage(), e.getCause());
            redirectAttributes.addFlashAttribute("message", "L'équipe n'a pas été trouvée");
            return "redirect:/equipes";
        }

        return "redirect:/equipe/" + team.getId();
    }

    @PreAuthorize("hasRole('ROLE_HRD')")
    @GetMapping("/employe/supprimer/{id}")
    public String submitUpdateTeamForm(@PathVariable String id) {
        if(employeeService.exists(id)) {
            employeeService.deleteEmployee(id);
        } else {
            log.error("Employee doesn't exist");
        }
        return "redirect:/employes";
    }

    // @galliou FIXME
    @PostMapping
    public String processSetPasswordForm(EmployeeEntity user,
                                         BindingResult result,
                                         HttpServletRequest request) {

        if (result.hasErrors()) {
            return "addEmployee";
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        System.out.println(token.getToken());
        token.setUser(user);
        System.out.println(token.getUser());
        token.setExpiryDate(30);
        System.out.println(token.getExpiryDate());
        System.out.println(token.isExpired());
        tokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("no-reply@leavemanager.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Demande de réinitialisation de mot de passe");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "https://leave-manager.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/resetPassword/pwd?token=" + token.getToken());
        mail.setModel(model);
        //System.out.println(mail.getModel());
        emailService.sendEmail(mail);
        // TODO add une div html pour success voir page login.html
        return "redirect:/employe/ajouter?success";

    }
}
