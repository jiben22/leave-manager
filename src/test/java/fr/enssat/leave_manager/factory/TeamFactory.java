package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.Employee;
import fr.enssat.leave_manager.model.Team;

import java.util.HashSet;
import java.util.Set;

public class TeamFactory {

    public static Team getTeam() {

        Set<Employee> employees = new HashSet<>();
        employees.add(EmployeeFactory.getEmployee());

        Team team = new Team();
        return team.builder()
                .name("Avengers")
                .teamLeader(TeamLeaderFactory.getTeamLeader())
                .department(DepartmentFactory.getDepartment())
                .employeeList(employees)
                .build();
    }
}
