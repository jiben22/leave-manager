package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.TeamLeaderEntity;

import java.util.HashSet;
import java.util.Optional;

public class TeamLeaderFactory {

    public static TeamLeaderEntity getTeamLeader() {
        return TeamLeaderEntity.builder()
                .employee(EmployeeFactory.getEmployee())
                .teamList(new HashSet<>())
                .build();
    }

    public static TeamLeaderEntity getTeamLeader2() {
        // this employee already exist on 'employee' table to respect relation
        EmployeeEntity emp2 = EmployeeFactory.getEmployee();
        return TeamLeaderEntity.builder()
                .eid(emp2.getEid())
                .employee(emp2)
                .teamList(new HashSet<>())
                .build();
    }

    public static TeamLeaderEntity getTeamLeader3() {
        // this employee already exist on 'employee' table to respect relation
        EmployeeEntity emp3 = EmployeeFactory.getEmployee3();
        return TeamLeaderEntity.builder()
                .eid(emp3.getEid())
                .employee(emp3)
                .teamList(new HashSet<>())
                .build();
    }
}
