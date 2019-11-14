package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.LeaveRequestEntity;
import fr.enssat.leave_manager.model.TypeOfLeaveEntity;

import java.util.HashSet;
import java.util.Set;

public class TypeOfLeaveFactory {

    public static TypeOfLeaveEntity getTypeOfLeave() {

        Set<LeaveRequestEntity> leaveRequests = new HashSet<>();
//        leaveRequests.add(LeaveRequestFactory.getLeaveRequest());

        return TypeOfLeaveEntity.builder()
                .name("Congé payé")
                .description("Par défaut, 25 par an, renouvelé le 1er janvier")
                .leaveRequests(leaveRequests)
                .build();
    }
}
