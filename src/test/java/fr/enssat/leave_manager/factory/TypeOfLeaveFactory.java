package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.TypeOfLeaveEntity;

import java.util.HashSet;
import java.util.Optional;

public class TypeOfLeaveFactory {

    public static TypeOfLeaveEntity getTypeOfLeave() {
        return TypeOfLeaveEntity.builder()
                .name("Congé payé")
                .description("Par défaut, 25 par an, renouvelé le 1er mai")
                .leaveRequests(new HashSet<>())
                .build();
    }

    public static Optional<TypeOfLeaveEntity> getTypeOfLeave1() {
        return Optional.ofNullable(TypeOfLeaveEntity.builder()
                .id("TYPEOFLEAVE-157314099170606-0001")
                .name("CA")
                .description("Congés Annuels")
                .leaveRequests(new HashSet<>())
                .build());
    }

    public static Optional<TypeOfLeaveEntity> getTypeOfLeave2() {
        return Optional.ofNullable(TypeOfLeaveEntity.builder()
                .id("TYPEOFLEAVE-157314099170606-0002")
                .name("RTT")
                .description("Réduction du temps de travail")
                .leaveRequests(new HashSet<>())
                .build());
    }

    public static Optional<TypeOfLeaveEntity> getTypeOfLeave4() {
        return Optional.ofNullable(TypeOfLeaveEntity.builder()
                .id("TYPEOFLEAVE-157314099170606-0004")
                .name("CF")
                .description("Congés Forcés")
                .leaveRequests(new HashSet<>())
                .isArchived(true)
                .build());
    }
}
