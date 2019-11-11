package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.repository.TeamLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamLeaderServiceImpl implements TypeOfLeaveService {

    private final TeamLeaderRepository teamLeaderRepository;

    @Autowired
    public TeamLeaderServiceImpl(TeamLeaderRepository teamLeaderRepository) {
        this.teamLeaderRepository = teamLeaderRepository;
    }
}
