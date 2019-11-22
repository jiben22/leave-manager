package fr.enssat.leave_manager.service.exception.not_found;

public class DepartmentNotFoundException extends NotFoundException {
    public DepartmentNotFoundException(String msg) {
        super("Department "+msg);
    }
}
