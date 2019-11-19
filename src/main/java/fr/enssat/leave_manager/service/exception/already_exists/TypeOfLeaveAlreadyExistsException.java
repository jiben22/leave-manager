package fr.enssat.leave_manager.service.exception.already_exists;

import fr.enssat.leave_manager.model.TypeOfLeaveEntity;

public class TypeOfLeaveAlreadyExistsException extends AlreadyExistsException {
    public TypeOfLeaveAlreadyExistsException(TypeOfLeaveEntity t) {
        super(t.toString());
    }
}
