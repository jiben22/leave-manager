package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.Team;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface TeamService {
    Team getTeam(String id);
    List<Team> getTeamByName(String name);
    List<Team> getTeams();
    Team addTeam(Team team);
    Team editTeam(Team team);
    void deleteTeam(String id);
    void deleteTeam(Team team);
}
