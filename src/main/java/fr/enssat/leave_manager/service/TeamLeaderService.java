package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.TeamLeaderEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface TeamLeaderService {
    boolean exists(String id);
    TeamLeaderEntity getTeamLeader(String id);
    List<TeamLeaderEntity> getTeamLeaders();
    TeamLeaderEntity addTeamLeader(TeamLeaderEntity teamLeader);
    TeamLeaderEntity editTeamLeader(TeamLeaderEntity teamLeader);
    void deleteTeamLeader(String id);
    void deleteTeamLeader(TeamLeaderEntity teamLeader);
}
