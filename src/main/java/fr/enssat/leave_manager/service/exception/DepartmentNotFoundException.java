package fr.enssat.leave_manager.service.exception;

public class DepartmentNotFoundException extends NotFoundException {
    public DepartmentNotFoundException(String msg) {
        super("Department "+msg);
    }
}
