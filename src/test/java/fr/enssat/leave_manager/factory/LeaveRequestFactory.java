package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.LeaveRequestEntity;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;

import java.time.LocalDateTime;
import java.util.Optional;

public class LeaveRequestFactory {

    public static LeaveRequestEntity getLeaveRequest() {
        return LeaveRequestEntity.builder()
                .reason("Vacances")
                .startingDate(LocalDateTime
                        .of(2020, 11, 22, 0, 0, 0))
                .endingDate(LocalDateTime
                        .of(2020, 11, 25, 23, 59, 0))
                .hrComment("")
                .employee(EmployeeFactory.getEmployee1())
                .typeOfLeave(TypeOfLeaveFactory.getTypeOfLeave2())
                .status(LeaveStatus.ACCEPTED)
                .build();
    }

    public static LeaveRequestEntity getLongLeaveRequest() {
        return LeaveRequestEntity.builder()
                .reason("Vacances")
                .startingDate(LocalDateTime
                        .of(2019, 11, 22, 0, 0, 0))
                .endingDate(LocalDateTime
                        .of(2020, 11, 25, 0, 0, 0))
                .hrComment("")
                .employee(EmployeeFactory.getEmployee2().get())
                .typeOfLeave(TypeOfLeaveFactory.getTypeOfLeave1().get())
                .status(LeaveStatus.ACCEPTED)
                .build();
    }

    public static LeaveRequestEntity getLeaveRequest3() {
        return LeaveRequestEntity.builder()
                .lrid("LEAVEREQUEST-157314099170606-0003")
                .reason("fils malade")
                .creationDate(LocalDateTime
                        .of(2019, 11, 17, 18, 10, 0))
                .lastEditionDate(LocalDateTime
                        .of(2019, 11, 17, 18, 10, 0))
                .startingDate(LocalDateTime
                        .of(2019, 11, 17, 0, 0, 0))
                .endingDate(LocalDateTime
                        .of(2019, 11, 17, 23, 29, 0))
                .hrComment("Bon courage")
                .employee(EmployeeFactory.getEmployee3())
                .typeOfLeave(TypeOfLeaveFactory.getTypeOfLeave1().get())
                .status(LeaveStatus.ACCEPTED)
                .build();
    }

    public static Optional<LeaveRequestEntity> getOptLeaveRequest3() {
        return Optional.ofNullable(getLeaveRequest3());
    }

    public static LeaveRequestEntity getLeaveRequest1() {
        return LeaveRequestEntity.builder()
                .lrid("LEAVEREQUEST-157314099170606-0001")
                .reason("vacances")
                .creationDate(LocalDateTime
                        .of(2019, 11, 17, 18, 10, 0))
                .lastEditionDate(LocalDateTime
                        .of(2019, 11, 17, 18, 10, 0))
                .startingDate(LocalDateTime
                        .of(2019, 11, 20, 0, 0, 0))
                .endingDate(LocalDateTime
                        .of(2019, 11, 28, 23, 29, 0))
                .hrComment("")
                .employee(EmployeeFactory.getEmployee1())
                .typeOfLeave(TypeOfLeaveFactory.getTypeOfLeave1().get())
                .status(LeaveStatus.PENDING)
                .build();
    }

    public static LeaveRequestEntity getLeaveRequest2() {
        return LeaveRequestEntity.builder()
                .lrid("LEAVEREQUEST-157314099170606-0002")
                .reason("jour de repos")
                .creationDate(LocalDateTime
                        .of(2019, 11, 17, 18, 10, 0))
                .lastEditionDate(LocalDateTime
                        .of(2019, 11, 17, 18, 10, 0))
                .startingDate(LocalDateTime
                        .of(2019, 11, 18, 0, 0, 0))
                .endingDate(LocalDateTime
                        .of(2019, 11, 18, 23, 29, 0))
                .hrComment("")
                .employee(EmployeeFactory.getEmployee1())
                .typeOfLeave(TypeOfLeaveFactory.getTypeOfLeave2())
                .status(LeaveStatus.CANCELLED)
                .build();
    }

    public static LeaveRequestEntity getLeaveRequest4() {
        return LeaveRequestEntity.builder()
                .lrid("LEAVEREQUEST-157314099170606-0004")
                .reason("pourquoi pas")
                .creationDate(LocalDateTime
                        .of(2019, 11, 17, 18, 10, 0))
                .lastEditionDate(LocalDateTime
                        .of(2019, 11, 17, 18, 10, 0))
                .startingDate(LocalDateTime
                        .of(2019, 11, 22, 0, 0, 0))
                .endingDate(LocalDateTime
                        .of(2019, 11, 25, 23, 29, 0))
                .hrComment("ce nest pas une raison valable")
                .employee(EmployeeFactory.getEmployee1())
                .typeOfLeave(TypeOfLeaveFactory.getTypeOfLeave1().get())
                .status(LeaveStatus.DECLINED)
                .build();
    }
}
