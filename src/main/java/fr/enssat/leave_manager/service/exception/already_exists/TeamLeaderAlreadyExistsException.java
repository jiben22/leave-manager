package fr.enssat.leave_manager.service.exception.already_exists;

import fr.enssat.leave_manager.model.TeamLeaderEntity;

public class TeamLeaderAlreadyExistsException extends AlreadyExistsException {
    public TeamLeaderAlreadyExistsException(TeamLeaderEntity teamLeader) {
        super(teamLeader.toString());
    }
}
