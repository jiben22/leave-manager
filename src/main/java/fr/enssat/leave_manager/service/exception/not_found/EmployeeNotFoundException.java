package fr.enssat.leave_manager.service.exception.not_found;

public class EmployeeNotFoundException extends NotFoundException {
    public EmployeeNotFoundException(String msg) {
        super("Employee "+msg);
    }
}
