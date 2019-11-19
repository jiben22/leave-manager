package fr.enssat.leave_manager.service.exception.already_exists;

import fr.enssat.leave_manager.model.DepartmentEntity;
import fr.enssat.leave_manager.service.exception.already_exists.AlreadyExistsException;

public class DepartmentAlreadyExistsException extends AlreadyExistsException {
    public DepartmentAlreadyExistsException(DepartmentEntity dept) {
        super(dept.toString());
    }
}
