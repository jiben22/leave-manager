package fr.enssat.leave_manager.service.exception.not_found;

public class TypeOfLeaveNotFoundException extends NotFoundException {
    public TypeOfLeaveNotFoundException(String msg) {
        super("TypeOfLeave "+msg);
    }
}
