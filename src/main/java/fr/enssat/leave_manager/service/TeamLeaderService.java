package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.TeamLeader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface TeamLeaderService {
    TeamLeader getTeamLeader(String id);
    List<TeamLeader> getTeamLeaders();
    TeamLeader addTeamLeader(TeamLeader teamLeader);
    TeamLeader editTeamLeader(TeamLeader teamLeader);
    void deleteTeamLeader(String id);
    void deleteTeamLeader(TeamLeader teamLeader);
}
