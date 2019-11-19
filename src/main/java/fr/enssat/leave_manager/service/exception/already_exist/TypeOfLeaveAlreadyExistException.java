package fr.enssat.leave_manager.service.exception.already_exist;

import fr.enssat.leave_manager.model.TypeOfLeaveEntity;

public class TypeOfLeaveAlreadyExistException extends AlreadyExistException {
    public TypeOfLeaveAlreadyExistException(TypeOfLeaveEntity t) {
        super(t.toString());
    }
}
