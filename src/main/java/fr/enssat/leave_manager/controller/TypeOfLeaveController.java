package fr.enssat.leave_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TypeOfLeaveController {

    @GetMapping("/types-conges")
    public String showTypesOfLeaves(Model model) {

        model.addAttribute("title", "Liste des types de congés");
        return "typesOfLeaves";
    }

    @GetMapping("/type-conges/ajouter")
    public String showAddTypeOfLeaves(Model model) {

        model.addAttribute("title", "Ajouter un type de congés");
        return "addtTypeOfLeaves";
    }

    @GetMapping("/types-conges/modifier")
    public String showUpdateTypeOfLeaves(Model model) {

        model.addAttribute("title", "Modifier un type de congés");
        return "updateTypeOfLeaves";
    }
}
