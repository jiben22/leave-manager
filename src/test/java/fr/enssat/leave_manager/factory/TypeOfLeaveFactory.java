package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.LeaveRequest;
import fr.enssat.leave_manager.model.TypeOfLeave;

import java.util.HashSet;
import java.util.Set;

public class TypeOfLeaveFactory {

    public static TypeOfLeave getTypeOfLeave() {

        Set<LeaveRequest> leaveRequests = new HashSet<>();
//        leaveRequests.add(LeaveRequestFactory.getLeaveRequest());

        TypeOfLeave typeOfLeave = new TypeOfLeave();
        return typeOfLeave.builder()
                .name("Congé payé")
                .description("Par défaut, 25 par an, renouvellé le 1er janvier")
                .leaveRequests(leaveRequests)
                .build();
    }
}
