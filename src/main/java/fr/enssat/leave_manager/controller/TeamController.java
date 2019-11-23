package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.TeamEntity;
import fr.enssat.leave_manager.service.TeamService;
import fr.enssat.leave_manager.service.impl.TeamServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TeamController {

    Logger logger = LoggerFactory.getLogger(TeamController.class);

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamServiceImpl teamService) {
        this.teamService = teamService;
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
