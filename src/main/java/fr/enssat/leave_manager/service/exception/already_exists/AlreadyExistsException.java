package fr.enssat.leave_manager.service.exception.already_exists;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String msg) {
        super(msg+" already exists !");
    }
}
