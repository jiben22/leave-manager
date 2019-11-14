package fr.enssat.leave_manager.service.exception;

public class TeamLeaderNotFoundException extends NotFoundException {
    public TeamLeaderNotFoundException(String msg) {
        super("TeamLeader "+msg);
    }
}
