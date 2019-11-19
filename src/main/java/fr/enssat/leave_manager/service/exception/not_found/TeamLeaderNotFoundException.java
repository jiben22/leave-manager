package fr.enssat.leave_manager.service.exception.not_found;

public class TeamLeaderNotFoundException extends NotFoundException {
    public TeamLeaderNotFoundException(String msg) {
        super("TeamLeader "+msg);
    }
}
