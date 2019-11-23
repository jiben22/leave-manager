package fr.enssat.leave_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeaveRequestController {

    @GetMapping("/demandes-conges")
    public String showLeavesRequests(Model model) {

        model.addAttribute("title", "Mes demandes de congés");
        return "leavesRequests";
    }

    @GetMapping("/demande-conges/ajouter")
    public String showAddLeavesRequest(Model model) {

        model.addAttribute("title", "Ajouter une demande de congés");
        return "leavesRequests";
    }

    @GetMapping("/demande-conges/modifier")
    public String showUpdateLeavesRequest(Model model) {

        model.addAttribute("title", "Modifier une demande de congés");
        return "updateLeavesRequest";
    }
}
