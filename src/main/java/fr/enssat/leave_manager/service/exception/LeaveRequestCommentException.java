package fr.enssat.leave_manager.service.exception;

import fr.enssat.leave_manager.service.LeaveRequestService;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;

public class LeaveRequestCommentException extends RuntimeException {
    public LeaveRequestCommentException() {
        super("Hr Comment field should not be empty if status is "+ LeaveStatus.DECLINED.name());
    }
}
