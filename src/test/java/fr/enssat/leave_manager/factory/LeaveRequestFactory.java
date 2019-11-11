package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.LeaveRequest;

import java.time.LocalDateTime;
import java.time.Month;

public class LeaveRequestFactory {

    public static LeaveRequest getLeaveRequest() {

        LeaveRequest leaveRequest = new LeaveRequest();
        return leaveRequest.builder()
                .reason("Vacances")
                .starting_date(LocalDateTime
                        .of(2019, Month.DECEMBER, 22, 0, 0, 0))
                .ending_date(LocalDateTime
                        .of(2020, Month.JANUARY, 05, 0, 0, 0))
                .hr_comment("Commentaires")
                .employee(EmployeeFactory.getEmployee())
                .typeOfLeave(TypeOfLeaveFactory.getTypeOfLeave())
                .status(LeaveRequest.LeaveStatus.ACCEPTED)
                .build();
    }
}
