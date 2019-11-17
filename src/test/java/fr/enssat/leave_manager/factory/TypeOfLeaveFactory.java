package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.TypeOfLeaveEntity;

import java.util.HashSet;

public class TypeOfLeaveFactory {

    public static TypeOfLeaveEntity getTypeOfLeave() {
        return TypeOfLeaveEntity.builder()
                .name("Congé payé")
                .description("Par défaut, 25 par an, renouvelé le 1er mai")
                .leaveRequests(new HashSet<>())
                .build();
    }

    public static TypeOfLeaveEntity getTypeOfLeave1() {
        return TypeOfLeaveEntity.builder()
                .id("TYPEOFLEAVE-157314099170606-0001")
                .name("CA")
                .description("Congés Annuels")
                .leaveRequests(new HashSet<>())
                .build();
    }
}
