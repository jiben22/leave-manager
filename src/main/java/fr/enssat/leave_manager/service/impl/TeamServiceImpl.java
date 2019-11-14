package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.repository.TeamRepository;
import fr.enssat.leave_manager.service.TypeOfLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TypeOfLeaveService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
}
