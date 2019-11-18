package fr.enssat.leave_manager.service.exception.not_found;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg+" not found !");
    }
}
