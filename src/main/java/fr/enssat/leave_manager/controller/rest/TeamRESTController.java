package fr.enssat.leave_manager.controller.rest;

import fr.enssat.leave_manager.controller.utils.JacksonMapper;
import fr.enssat.leave_manager.model.TeamEntity;
import fr.enssat.leave_manager.service.TeamService;
import fr.enssat.leave_manager.service.impl.TeamServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamRESTController {

    Logger logger = LoggerFactory.getLogger(TeamRESTController.class);

    private final TeamService teamService;

    @Autowired
    public TeamRESTController(TeamServiceImpl teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/api/team/{id}")
    public ResponseEntity<String> getTeamById(@PathVariable String id) {

        logger.debug("GET /api/team/{id}");

        TeamEntity team = teamService.getTeam(id);
        String jsonTeam = JacksonMapper.convertTeamToJSON(team);
        return new ResponseEntity<>(jsonTeam, HttpStatus.OK);
    }
}
