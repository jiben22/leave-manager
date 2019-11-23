package fr.enssat.leave_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeamController {

    @GetMapping("/equipes")
    public String showTeams(Model model) {

        model.addAttribute("title", "Liste des équipes");
        return "teams";
    }

    @GetMapping("/equipe/ajouter")
    public String showAddTeam(Model model) {

        model.addAttribute("title", "Ajouter une équipe");
        return "addTeam";
    }

    @GetMapping("/equipe/modifier")
    public String showUpdateTeam(Model model) {

        model.addAttribute("title", "Modifier l'équipe");
        return "updateTeam";
    }
}
