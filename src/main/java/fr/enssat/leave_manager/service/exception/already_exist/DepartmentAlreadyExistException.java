package fr.enssat.leave_manager.service.exception.already_exist;

import fr.enssat.leave_manager.model.DepartmentEntity;

public class DepartmentAlreadyExistException extends AlreadyExistException {
    public DepartmentAlreadyExistException(DepartmentEntity dept) {
        super(dept.toString());
    }
}
