package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.TeamEntity;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TeamFactory {

    public static TeamEntity getTeam() {

        Set<EmployeeEntity> employees = new HashSet<>();
        employees.add(EmployeeFactory.getEmployee3());

        return TeamEntity.builder()
                .name("Avengers")
                .teamLeader(TeamLeaderFactory.getTeamLeader2().get())
                .department(DepartmentFactory.getDepartment1().get())
                .employeeList(employees)
                .build();
    }

    public static Optional<TeamEntity> getTeam2() {

        Set<EmployeeEntity> employees = new HashSet<>();
        employees.add(EmployeeFactory.getEmployee2().get());
        // Missing an employee

        return Optional.ofNullable(TeamEntity.builder()
                .id("TEAM-157314099170606-0002")
                .name("Bifrost traveler")
                .teamLeader(TeamLeaderFactory.getTeamLeader2().get())
                .department(DepartmentFactory.getDepartment1().get())
                .employeeList(employees)
                .build());
    }

    public static Optional<TeamEntity> getTeam3() {

        Set<EmployeeEntity> employees = new HashSet<>();
        employees.add(EmployeeFactory.getEmployee2().get());

        return Optional.ofNullable(TeamEntity.builder()
                .id("TEAM-157314099170606-0003")
                .name("Frozen")
                .teamLeader(TeamLeaderFactory.getTeamLeader2().get()) // Bad team leader
                .department(DepartmentFactory.getDepartment1().get()) // Bad department
                .employeeList(employees)
                .build());
    }

    public static Optional<TeamEntity> getTeamNotFound() {

        Set<EmployeeEntity> employees = new HashSet<>();
        employees.add(EmployeeFactory.getEmployee2().get());

        return Optional.ofNullable(TeamEntity.builder()
                .id("TEAM-157314099170606-9999")
                .name("Not found")
                .teamLeader(TeamLeaderFactory.getTeamLeader2().get()) // Bad team leader
                .department(DepartmentFactory.getDepartment1().get()) // Bad department
                .employeeList(employees)
                .build());
    }
}
