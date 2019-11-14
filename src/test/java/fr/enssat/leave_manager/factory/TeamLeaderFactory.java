package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.TeamEntity;
import fr.enssat.leave_manager.model.TeamLeaderEntity;

import java.util.HashSet;
import java.util.Set;

public class TeamLeaderFactory {

    public static TeamLeaderEntity getTeamLeader() {

        Set<TeamEntity> teams = new HashSet<>();
//        teams.add(TeamFactory.getTeam());

        return TeamLeaderEntity.builder()
                .employee(EmployeeFactory.getEmployee())
                .teamList(teams)
                .build();
    }
}
