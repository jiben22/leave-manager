package fr.enssat.leave_manager.service.exception;

public class TypeOfLeaveException extends NotFoundException {
    public TypeOfLeaveException(String msg) {
        super("TypeOfLeave "+msg);
    }
}
