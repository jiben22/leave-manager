package fr.enssat.leave_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @GetMapping("/employes")
    public String showEmployees(Model model) {

        model.addAttribute("title", "Liste des employés");
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
