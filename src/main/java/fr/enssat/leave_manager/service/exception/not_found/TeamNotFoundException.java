package fr.enssat.leave_manager.service.exception.not_found;

public class TeamNotFoundException extends NotFoundException {
    public TeamNotFoundException(String msg) {
        super("Team "+msg);
    }
}
