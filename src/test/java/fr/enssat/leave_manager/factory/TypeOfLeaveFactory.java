package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.TypeOfLeave;

public class TypeOfLeaveFactory {

    public static TypeOfLeave getTypeOfLeave() {

        TypeOfLeave typeOfLeave = new TypeOfLeave();
        return typeOfLeave.builder()
                .name("Congé payé")
                .description("Par défaut, 25 par an, renouvellé le 1er janvier")
//                .leaveRequests()
                .build();
    }
}
