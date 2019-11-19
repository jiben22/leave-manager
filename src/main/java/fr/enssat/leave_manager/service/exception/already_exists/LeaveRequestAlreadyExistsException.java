package fr.enssat.leave_manager.service.exception.already_exists;

import fr.enssat.leave_manager.model.LeaveRequestEntity;

public class LeaveRequestAlreadyExistsException extends AlreadyExistsException {
    public LeaveRequestAlreadyExistsException(LeaveRequestEntity lr) {
        super(lr.toString());
    }
}
