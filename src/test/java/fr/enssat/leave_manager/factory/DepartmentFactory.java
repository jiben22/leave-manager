package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.DepartmentEntity;

public class DepartmentFactory {

    public static DepartmentEntity getDepartment() {

//        Set<Team> teams = new HashSet<>();
//        teams.add(TeamFactory.getTeam());


        return DepartmentEntity.builder()
                .name("Sauver le monde")
//                .teamList(teams)
                .build();
    }
}
