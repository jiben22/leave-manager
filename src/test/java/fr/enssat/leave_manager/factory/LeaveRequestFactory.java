package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.LeaveRequestEntity;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;

import java.time.LocalDateTime;
import java.time.Month;

public class LeaveRequestFactory {

    public static LeaveRequestEntity getLeaveRequest() {
        return LeaveRequestEntity.builder()
                .reason("Vacances")
                .startingDate(LocalDateTime
                        .of(2019, Month.DECEMBER, 22, 0, 0, 0))
                .endingDate(LocalDateTime
                        .of(2020, Month.JANUARY, 5, 0, 0, 0))
                .hrComment("")
                .employee(EmployeeFactory.getEmployee())
                .typeOfLeave(TypeOfLeaveFactory.getTypeOfLeave())
                .status(LeaveStatus.ACCEPTED)
                .build();
    }
}
