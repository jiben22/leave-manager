package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.*;
import fr.enssat.leave_manager.repository.PasswordResetTokenRepository;
import fr.enssat.leave_manager.service.EmailService;
import fr.enssat.leave_manager.service.EmployeeService;
import fr.enssat.leave_manager.service.TeamService;
import fr.enssat.leave_manager.service.exception.not_found.EmployeeNotFoundException;
import fr.enssat.leave_manager.service.exception.not_found.TeamNotFoundException;
import fr.enssat.leave_manager.service.impl.EmployeeServiceImpl;
import fr.enssat.leave_manager.service.impl.TeamServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    public EmployeeController(EmployeeServiceImpl employeeService, TeamServiceImpl teamService) {
        this.employeeService = employeeService;
        this.teamService = teamService;
    }

    @GetMapping("/employes")
    public String showEmployees(Model model) {

        log.info("GET /employes");

        model.addAttribute("title", "Liste des employés");

        // Get employees
        List<EmployeeEntity> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);

        return "employees";
    }

    @GetMapping("/employe/{id}")
    public String showEmployeById(@PathVariable String id, Model model) {

        log.info("GET /employe/" + id);

        model.addAttribute("title", "Visualiser l'employé");

        // Get employe by id
        EmployeeEntity employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);

        return "showEmployee";
    }

    @GetMapping("/employe/ajouter")
    public String showAddEmployeeForm(Model model) {

        log.info("GET /employe/ajouter");

        model.addAttribute("title", "Ajouter un employé");
        model.addAttribute("employee", new EmployeeEntity());

        // Get teams
        List<TeamEntity> teams =  teamService.getTeams();
        model.addAttribute("teams", teams);

        return "addEmployeeForm";
    }

    @PostMapping("/employe/ajouter")
    public String submitAddEmployeeForm(@Valid @ModelAttribute("employee") EmployeeEntity employee,
                                        BindingResult result, Model model,
                                        RedirectAttributes redirectAttributes) {

        log.info("POST /employe/ajouter");

        // Get teams
        List<TeamEntity> teams =  teamService.getTeams();
        model.addAttribute("teams", teams);

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
                } catch (Exception e1) {
                    log.error(e1.getMessage() + e1.getCause());
                    redirectAttributes.addFlashAttribute("message", "Impossible d'enregister l'employé");

                    return "redirect:/employes";
                }
            }
        }

        return "redirect:/employe/" + employee.getEid();
    }

    @GetMapping("/employe/modifier/{id}")
    public String showUpdateEmployeeForm(@PathVariable String id, Model model) {

        log.info("GET /employe/modifier/" + id);

        model.addAttribute("title", "Modifier l'employé");

        // Get employee by id
        EmployeeEntity employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);

        // Get teams
        List<TeamEntity> teams =  teamService.getTeams();
        model.addAttribute("teams", teams);

        return "updateEmployeeForm";
    }

    @PostMapping("/employe/modifier/{id}")
    public String submitUpdateEmployeeForm(@PathVariable String id,
                                           @Valid @ModelAttribute("employee") EmployeeEntity employee,
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
        // TODO: add all employees to list

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
                                              RedirectAttributes redirectAttributes) {

        log.info("POST /employe/ajouter/" + teamId);

        model.addAttribute("title", "Ajouter un employé dans l'équipe");

        // Set team in the list
        List<TeamEntity> teams =  new ArrayList<>();
        teams.add(team);
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

    // @galliou FIXME
    @PostMapping
    public String processSetPasswordForm(EmployeeEntity user,
                                         BindingResult result,
                                         HttpServletRequest request) {

        if (result.hasErrors()) {
            return "addEmployee";
        }
        /*EmployeeEntity user = employeeService.getEmployeeByEmail(form.getEmail());
        if (user == null) {
            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return "addEmployee";
        }*/

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
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);
        //System.out.println(mail.getModel());
        emailService.sendEmail(mail);
        // TODO add une div html pour success voir page login.html
        return "redirect:/employe/ajouter?success";

    }
}
