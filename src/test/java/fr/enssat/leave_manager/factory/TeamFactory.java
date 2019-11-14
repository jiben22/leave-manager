package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.TeamEntity;

import java.util.HashSet;
import java.util.Set;

public class TeamFactory {

    public static TeamEntity getTeam() {

        Set<EmployeeEntity> employees = new HashSet<>();
        employees.add(EmployeeFactory.getEmployee());

        return TeamEntity.builder()
                .name("Avengers")
                .teamLeader(TeamLeaderFactory.getTeamLeader())
                .department(DepartmentFactory.getDepartment())
                .employeeList(employees)
                .build();
    }
}
