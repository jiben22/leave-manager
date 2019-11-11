package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.TeamLeader;

public class TeamLeaderFactory {

    public static TeamLeader getTeamLeader() {

        TeamLeader teamLeader = new TeamLeader();
        return teamLeader.builder()
                .employee(EmployeeFactory.getEmployee())
//                .teamList()
                .build();
    }
}
