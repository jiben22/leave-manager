package fr.enssat.leave_manager.service.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg+" not found !");
    }
}
