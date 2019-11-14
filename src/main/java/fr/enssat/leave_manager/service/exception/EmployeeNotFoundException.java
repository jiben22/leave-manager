package fr.enssat.leave_manager.service.exception;

public class EmployeeNotFoundException extends NotFoundException {
    public EmployeeNotFoundException(String msg) {
        super("Employee "+msg);
    }
}
