package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.TeamEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface TeamService {
    boolean exists(String id);
    TeamEntity getTeam(String id);
    List<TeamEntity> getTeamByName(String name);
    List<TeamEntity> getTeams();
    TeamEntity addTeam(TeamEntity team);
    TeamEntity editTeam(TeamEntity team);
    void deleteTeam(String id);
}
