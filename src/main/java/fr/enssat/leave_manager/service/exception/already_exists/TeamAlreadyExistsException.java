package fr.enssat.leave_manager.service.exception.already_exists;

import fr.enssat.leave_manager.model.TeamEntity;

public class TeamAlreadyExistsException extends AlreadyExistsException {
    public TeamAlreadyExistsException(TeamEntity team) {
        super(team.toString());
    }
}
