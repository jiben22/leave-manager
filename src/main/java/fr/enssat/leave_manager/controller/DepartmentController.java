package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.DepartmentEntity;
import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.TeamEntity;
import fr.enssat.leave_manager.model.TeamLeaderEntity;
import fr.enssat.leave_manager.service.DepartmentService;
import fr.enssat.leave_manager.service.EmployeeService;
import fr.enssat.leave_manager.service.TeamLeaderService;
import fr.enssat.leave_manager.service.TeamService;
import fr.enssat.leave_manager.service.impl.DepartmentServiceImpl;
import fr.enssat.leave_manager.service.impl.EmployeeServiceImpl;
import fr.enssat.leave_manager.service.impl.TeamLeaderServiceImpl;
import fr.enssat.leave_manager.service.impl.TeamServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departement/ajouter")
    public String showAddDepartmentForm(Model model) {

        log.info("GET /departement/ajouter");

        model.addAttribute("title", "Ajouter un département");
        model.addAttribute("department", new DepartmentEntity());

        return "addDepartmentForm";
    }

    @PostMapping("/departement/ajouter")
    public String submitAddDepartmentForm(@Valid @ModelAttribute("department") DepartmentEntity department,
                                          BindingResult result, Model model,
                                          RedirectAttributes redirectAttributes) {

        log.info("POST /departement/ajouter");

        model.addAttribute("title", "Ajouter un département");

        // Check if form has errors
        if (result.hasErrors()) {
            log.info(result.toString());

            // Return form with errors
            return "addDepartmentForm";
        }

        // Save department
        try {
            departmentService.addDepartment(department);
        } catch (Exception e) {
            log.error(e.getMessage(), e.getCause());

            redirectAttributes.addFlashAttribute("message", "Impossible d'enregister le département");
        }

        return "redirect:/equipes";
    }

//    @GetMapping("/equipe/ajouter")
//    public String showAddTeamForm(Model model) {
//
//        log.info("GET /equipe/ajouter");
//
//        model.addAttribute("title", "Ajouter une équipe");
//        model.addAttribute("team", new TeamEntity());
//
//        // Get employees
//        List<EmployeeEntity> employees =  employeeService.getEmployees();
//        model.addAttribute("employees", employees);
//
//        // Get departments
//        List<DepartmentEntity> departments = departmentService.getDepartments();
//        model.addAttribute("departments", departments);
//
//        return "addTeamForm";
//    }
//
//    @GetMapping("/equipe/{id}")
//    public String showTeamById(@PathVariable String id, Model model) {
//
//        log.info("GET /equipe/" + id);
//
//        model.addAttribute("title", "Visualiser l'équipe");
//
//        // Get team by id
//        TeamEntity team = teamService.getTeam(id);
//        model.addAttribute("team", team);
//
//        return "showTeam";
//    }
//
//    @PostMapping("/equipe/ajouter")
//    public String submitAddTeamForm(@Valid @ModelAttribute("team") TeamEntity team,
//                                    @RequestParam("teamLeader") String teamLeaderId,
//                                    BindingResult result, Model model,
//                                    RedirectAttributes redirectAttributes) {
//
//        log.info("POST /equipe/ajouter");
//
//        model.addAttribute("title", "Ajouter une équipe");
//
//        if (result.hasErrors()) {
//            log.info(result.toString());
//
//            // Get employees
//            List<EmployeeEntity> employees =  employeeService.getEmployees();
//            model.addAttribute("employees", employees);
//
//            // Get departments
//            List<DepartmentEntity> departments = departmentService.getDepartments();
//            model.addAttribute("departments", departments);
//
//            // Return form with errors
//            return "addTeamForm";
//        }
//
//        addEmployeeToTeamLeader(teamLeaderId, team);
//
//        try {
//            // Save team
//            team = teamService.addTeam(team);
//        } catch (Exception e) {
//            log.error(e.getMessage() + e.getCause());
//            redirectAttributes.addFlashAttribute("message", "Impossible d'enregister l'équipe");
//
//            return "redirect:/equipes";
//        }
//
//        return "redirect:/equipe/" + team.getId();
//    }
//
//    @GetMapping("/equipe/modifier/{id}")
//    public String showUpdateTeamForm(@PathVariable String id,
//                                     Model model) {
//
//        log.info("GET /equipe/modifier/" + id);
//
//        model.addAttribute("title", "Modifier l'équipe");
//
//        // Get team by id
//        TeamEntity team = teamService.getTeam(id);
//        model.addAttribute("team", team);
//
//        // Get employees
//        List<EmployeeEntity> employees =  employeeService.getEmployees();
//        model.addAttribute("employees", employees);
//
//        // Get departments
//        List<DepartmentEntity> departments = departmentService.getDepartments();
//        model.addAttribute("departments", departments);
//
//        return "updateTeamForm";
//    }
//
//    @PostMapping("/equipe/modifier/{id}")
//    public String submitUpdateTeamForm(@PathVariable String id,
//                                       @RequestParam("teamLeader") String teamLeaderId,
//                                       @Valid @ModelAttribute("team") TeamEntity team,
//                                       BindingResult result, Model model,
//                                       RedirectAttributes redirectAttributes) {
//
//        log.info("POST /equipe/modifier/" + id);
//
//        if (result.hasErrors()) {
//            log.info(result.toString());
//
//            // Get employees
//            List<EmployeeEntity> employees =  employeeService.getEmployees();
//            model.addAttribute("employees", employees);
//
//            // Get departments
//            List<DepartmentEntity> departments = departmentService.getDepartments();
//            model.addAttribute("departments", departments);
//
//            // Return form with errors
//            return "updateTeamForm";
//        }
//
//        addEmployeeToTeamLeader(teamLeaderId, team);
//
//        try {
//            // Save team
//            team = teamService.editTeam(team);
//        } catch (Exception e) {
//            log.error(e.getMessage() + e.getCause());
//            redirectAttributes.addFlashAttribute("message", "Impossible de modifier l'équipe.");
//
//            return "redirect:/equipes";
//        }
//
//        return "redirect:/equipe/" + team.getId();
//    }
//
//    // Add employee to team leader
//    public void addEmployeeToTeamLeader(String teamLeaderId, TeamEntity team) {
//
//        // Add employee to team leader if doesn't exist
//        TeamLeaderEntity teamLeader;
//        if (!teamLeaderService.exists(teamLeaderId)) {
//            // Get employee
//            EmployeeEntity employee =
//                    employeeService.getEmployee(teamLeaderId);
//
//            // Save employee as team leader
//            teamLeader =
//                    teamLeaderService.addEmployeeToTeamLeader(employee);
//
//            log.info("Add team leader: " + teamLeader.getEid());
//        } else {
//            // Get team leader
//            teamLeader = teamLeaderService.getTeamLeader(teamLeaderId);
//        }
//
//        // Set team leader to team
//        team.setTeamLeader(teamLeader);
//        // Add team leader to employees list if doesn't exist
//        team.getEmployeeList().add(teamLeader.getEmployee());
//    }
//
//    @GetMapping("/equipe/supprimer/{id}")
//    public String submitUpdateTeamForm(@PathVariable String id) {
//        teamService.deleteTeam(id);
//        return "redirect:/equipes";
//    }
}