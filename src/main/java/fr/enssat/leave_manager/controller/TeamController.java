package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.TeamEntity;
import fr.enssat.leave_manager.service.TeamService;
import fr.enssat.leave_manager.service.impl.TeamServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TeamController {

    Logger logger = LoggerFactory.getLogger(TeamController.class);

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamServiceImpl teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/equipes")
    public ModelAndView showEmployees() {

        logger.info("GET /equipes called");

        String viewName = "teams";
        Map<String,Object> model = new HashMap<>();
        model.put("title", "Liste des équipes");

        // Get teams
        List<TeamEntity> teams = teamService.getTeams();
        model.put("teams", teams);

        return new ModelAndView(viewName, model);
    }
}
