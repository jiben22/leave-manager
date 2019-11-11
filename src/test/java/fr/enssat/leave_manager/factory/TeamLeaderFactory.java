package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.Team;
import fr.enssat.leave_manager.model.TeamLeader;

import java.util.HashSet;
import java.util.Set;

public class TeamLeaderFactory {

    public static TeamLeader getTeamLeader() {

        Set<Team> teams = new HashSet<>();
//        teams.add(TeamFactory.getTeam());

        TeamLeader teamLeader = new TeamLeader();
        return teamLeader.builder()
                .employee(EmployeeFactory.getEmployee())
                .teamList(teams)
                .build();
    }
}
