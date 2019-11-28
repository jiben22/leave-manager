package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.TeamEntity;
import fr.enssat.leave_manager.model.TeamLeaderEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeamLeaderService {
    boolean exists(String id);
    TeamLeaderEntity getTeamLeader(String id);
    List<TeamLeaderEntity> getTeamLeaders();
    TeamLeaderEntity addTeamLeader(TeamLeaderEntity teamLeader);
    TeamLeaderEntity addEmployeeToTeamLeader(EmployeeEntity employee);
    void deleteTeamLeader(String id);
}
