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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TeamController {

    Logger logger = LoggerFactory.getLogger(TeamController.class);

    private final TeamService teamService;

    private final EmployeeService employeeService;

    private final TeamLeaderService teamLeaderService;

    private final DepartmentService departmentService;

    @Autowired
    public TeamController(TeamServiceImpl teamService, EmployeeServiceImpl employeeService,
                          TeamLeaderServiceImpl teamLeaderService, DepartmentServiceImpl departmentService) {
        this.teamService = teamService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.teamLeaderService = teamLeaderService;
    }

    @GetMapping("/equipes")
    public String showTeams(Model model) {

        logger.debug("GET /equipes");

        model.addAttribute("title", "Liste des équipes");

        // Get teams
        List<TeamEntity> teams = teamService.getTeams();
        model.addAttribute("teams", teams);

        return "teams";
    }

    @GetMapping("/equipe/ajouter")
    public String showAddTeamForm(Model model) {

        logger.debug("GET /equipe/ajouter");

        model.addAttribute("title", "Ajouter une équipe");
        model.addAttribute("team", new TeamEntity());

        // Get employees
        List<EmployeeEntity> employees =  employeeService.getEmployees();
        model.addAttribute("employees", employees);

        // Get departments
        List<DepartmentEntity> departments = departmentService.getDepartments();
        model.addAttribute("departments", departments);

        return "addTeamForm";
    }

    @GetMapping("/equipe/{id}")
    public String showTeamById(@PathVariable String id, Model model) {

        logger.debug("GET /equipe/" + id);

        model.addAttribute("title", "Visualiser l'équipe");

        // Get team by id
        TeamEntity team = teamService.getTeam(id);
        model.addAttribute("team", team);

        return "showTeam";
    }

    @PostMapping("/equipe/ajouter")
    public String submitAddTeamForm(@Valid @ModelAttribute("team") TeamEntity team,
                                        BindingResult result, Model model,
                                        RedirectAttributes redirectAttributes) {

        logger.debug("POST /equipe/ajouter");

        if (result.hasErrors()) {
            logger.info(result.toString());

            // Get employees
            List<EmployeeEntity> employees =  employeeService.getEmployees();
            model.addAttribute("employees", employees);

            // Get departments
            List<DepartmentEntity> departments = departmentService.getDepartments();
            model.addAttribute("departments", departments);

            // Return form with errors
            return "addTeamForm";
        } else {
            try {
                logger.error(team.getTeamLeader().toString());
                team.setTeamLeader(team.getTeamLeader());
                // Save team
                team = teamService.addTeam(team);
            } catch (Exception e) {
                logger.error(e.getMessage() + e.getCause());
                redirectAttributes.addFlashAttribute("message", "Impossible d'enregister l'équipe");

                return "redirect:/equipes";
            }
        }

        return "redirect:/equipe/" + team.getId();
    }

    @GetMapping("/equipe/modifier/{id}")
    public String showUpdateTeamForm(@PathVariable String id, Model model) {

        logger.debug("GET /equipe/modifier/" + id);

        model.addAttribute("title", "Modifier l'équipe");

        // Get team by id
        TeamEntity team = teamService.getTeam(id);
        model.addAttribute("team", team);

        // Get employees
        List<EmployeeEntity> employees =  employeeService.getEmployees();
        model.addAttribute("employees", employees);

        // Get departments
        List<DepartmentEntity> departments = departmentService.getDepartments();
        model.addAttribute("departments", departments);

        return "updateTeamForm";
    }

    @PostMapping("/equipe/modifier/{id}")
    public String submitUpdateTeamForm(@PathVariable String id,
                                           @Valid @ModelAttribute("team") TeamEntity team,
                                           BindingResult result, Model model,
                                           RedirectAttributes redirectAttributes) {

        logger.debug("POST /equipe/modifier/" + id);

        if (result.hasErrors()) {
            logger.info(result.toString());

            // Get employees
            List<EmployeeEntity> employees =  employeeService.getEmployees();
            model.addAttribute("employees", employees);

            // Get departments
            List<DepartmentEntity> departments = departmentService.getDepartments();
            model.addAttribute("departments", departments);

            // Return form with errors
            return "updateTeamForm";
        } else {
            try {

                // Save team
                team = teamService.editTeam(team);
            } catch (Exception e) {
                logger.error(e.getMessage() + e.getCause());
                redirectAttributes.addFlashAttribute("message", "Impossible de modifier l'équipe.");

                return "redirect:/equipes";
            }
        }

        return "redirect:/equipe/" + team.getId();
    }

    @GetMapping("/equipe/supprimer/{id}")
    public String submitUpdateTeamForm(@PathVariable String id) {
        teamService.deleteTeam(id);
        return "redirect:/equipes";
    }
}
