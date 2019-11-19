package fr.enssat.leave_manager.service.exception.already_exist;

import fr.enssat.leave_manager.model.LeaveRequestEntity;

public class LeaveRequestAlreadyExistException extends AlreadyExistException {
    public LeaveRequestAlreadyExistException(LeaveRequestEntity lr) {
        super(lr.toString());
    }
}
