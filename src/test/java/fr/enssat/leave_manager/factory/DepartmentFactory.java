package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.Department;
import fr.enssat.leave_manager.model.Team;

import java.util.HashSet;
import java.util.Set;

public class DepartmentFactory {

    public static Department getDepartment() {

//        Set<Team> teams = new HashSet<>();
//        teams.add(TeamFactory.getTeam());

        Department department = new Department();
        return department.builder()
                .name("Sauver le monde")
//                .teamList(teams)
                .build();
    }
}
