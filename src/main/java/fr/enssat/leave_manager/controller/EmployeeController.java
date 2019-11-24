package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.DepartmentEntity;
import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.TeamEntity;
import fr.enssat.leave_manager.service.DepartmentService;
import fr.enssat.leave_manager.service.EmployeeService;
import fr.enssat.leave_manager.service.TeamService;
import fr.enssat.leave_manager.service.impl.DepartmentServiceImpl;
import fr.enssat.leave_manager.service.impl.EmployeeServiceImpl;
import fr.enssat.leave_manager.service.impl.TeamServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    private final TeamService teamService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService, TeamServiceImpl teamService) {
        this.employeeService = employeeService;
        this.teamService = teamService;
    }

    @GetMapping("/employes")
    public String showEmployees(Model model) {

        logger.debug("GET /employes");

        model.addAttribute("title", "Liste des employés");

        // Get employees
        List<EmployeeEntity> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);

        return "employees";
    }

    @GetMapping("/employe/{id}")
    public String showEmployeById(@PathVariable String id, Model model) {

        logger.debug("GET /employe/" + id);

        model.addAttribute("title", "Visualiser l'employé");

        // Get employe by id
        EmployeeEntity employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);

        return "showEmployee";
    }

    @GetMapping("/employe/ajouter")
    public String showAddEmployeeForm(Model model) {

        logger.debug("GET /employe/ajouter");

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

        logger.debug("POST /employe/ajouter");

        if (result.hasErrors()) {
            logger.info(result.toString());

            // Get teams
            List<TeamEntity> teams =  teamService.getTeams();
            model.addAttribute("teams", teams);

            // Return form with errors
            return "addEmployeeForm";
        } else {
            try {
                // Save employee
                employee = employeeService.addEmployee(employee);
            } catch (Exception e) {
                logger.error(e.getMessage() + e.getCause());
                redirectAttributes.addFlashAttribute("message", "Impossible d'enregister l'employé.");

                return "redirect:/employes";
            }
        }

        return "redirect:/employe/" + employee.getEid();
    }

    @GetMapping("/employe/modifier/{id}")
    public String showUpdateEmployeeForm(@PathVariable String id, Model model) {

        logger.debug("GET /employe/modifier/" + id);

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

        logger.debug("POST /employe/modifier/" + id);

        if (result.hasErrors()) {
            logger.info(result.toString());

            // Get teams
            List<TeamEntity> teams =  teamService.getTeams();
            model.addAttribute("teams", teams);

            // Return form with errors
            return "updateEmployeeForm";
        } else {
            try {
                // Save employee
                employeeService.editEmployee(employee);
            } catch (Exception e) {
                logger.error(e.getMessage() + e.getCause());
                redirectAttributes.addFlashAttribute("message", "Impossible de modifier l'employé.");

                return "redirect:/employes";
            }
        }

        return "redirect:/employe/" + employee.getEid();
    }
}
