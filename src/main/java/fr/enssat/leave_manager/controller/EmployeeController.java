package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.service.EmployeeService;
import fr.enssat.leave_manager.service.impl.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
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
    public String showAddEmployee(Model model) {

        model.addAttribute("title", "Ajouter un employé");
        return "addEmployee";
    }

    @GetMapping("/employe/modifier")
    public String showUpdateEmployee(Model model) {

        model.addAttribute("title", "Modifier l'employé");
        return "updateEmployee";
    }
}
