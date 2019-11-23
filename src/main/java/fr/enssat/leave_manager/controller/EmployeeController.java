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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/employe/ajouter")
    public String showAddEmployeeForm(Model model) {

        logger.debug("GET /employe/ajouter");

        model.addAttribute("title", "Ajouter un employé");
        model.addAttribute("employee", new EmployeeEntity());

        // Get teams
        List<TeamEntity> teams =  teamService.getTeams();
        model.addAttribute("teams", teams);

        return "addEmployee";
    }

    @PostMapping("/employe/ajouter")
    public String submitAddEmployeeForm(@Valid EmployeeEntity employee, BindingResult result) {

        logger.debug("POST /employe/ajouter");

        if (result.hasErrors()) {
            return "redirect:/employe/ajouter";
        } else {
            try {
                // Add default password
                employee.setPassword("aChanger@2019");
                // Save employee
                employeeService.addEmployee(employee);
            } catch (Exception e) {
                logger.error(e.getMessage() + e.getCause());
                return "redirect:/employes";
            }
        }

        return "redirect:/employes";
    }

    @GetMapping("/employe/modifier")
    public String showUpdateEmployeeForm(Model model) {

        model.addAttribute("title", "Modifier l'employé");
        return "updateEmployee";
    }
}
