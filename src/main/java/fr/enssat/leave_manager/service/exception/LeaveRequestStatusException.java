package fr.enssat.leave_manager.service.exception;

import fr.enssat.leave_manager.utils.enums.LeaveStatus;

public class LeaveRequestStatusException extends RuntimeException {
    public LeaveRequestStatusException(LeaveStatus actual, LeaveStatus wanted) {
        super("LeaveRequest with '"+actual.name()+"' status can't change for '"+wanted.name()+"' status !");
    }
}
