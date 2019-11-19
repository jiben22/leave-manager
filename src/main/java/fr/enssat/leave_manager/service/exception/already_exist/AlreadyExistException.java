package fr.enssat.leave_manager.service.exception.already_exist;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(String msg) {
        super(msg+" already exist !");
    }
}
