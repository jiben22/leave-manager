package fr.enssat.leave_manager.service.exception.already_exists;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.service.exception.already_exists.AlreadyExistsException;

public class EmployeeAlreadyExistException extends AlreadyExistsException {
    public EmployeeAlreadyExistException(EmployeeEntity emp) {
        super(emp.toString());
    }
}
