package fr.enssat.leave_manager.service.exception.already_exist;

import fr.enssat.leave_manager.model.EmployeeEntity;

public class EmployeeAlreadyExistException extends AlreadyExistException {
    public EmployeeAlreadyExistException(EmployeeEntity emp) {
        super(emp.toString());
    }
}
