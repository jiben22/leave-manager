package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.DepartmentEntity;

import java.util.Optional;

public class DepartmentFactory {

    public static DepartmentEntity getDepartment() {

//        Set<Team> teams = new HashSet<>();
//        teams.add(TeamFactory.getTeam());


        return DepartmentEntity.builder()
                .name("Sauver le monde")
//                .teamList(teams)
                .build();
    }

    public static Optional<DepartmentEntity> getDepartment1(){
        return Optional.ofNullable(DepartmentEntity.builder()
                .id("DEPARTMENT-157314099170606-0001")
                .name("Space")
                .build());
    }
}
