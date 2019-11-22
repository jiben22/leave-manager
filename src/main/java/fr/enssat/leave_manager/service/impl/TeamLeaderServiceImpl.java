package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.TeamLeaderEntity;
import fr.enssat.leave_manager.repository.TeamLeaderRepository;
import fr.enssat.leave_manager.service.TeamLeaderService;
import fr.enssat.leave_manager.service.exception.already_exists.TeamLeaderAlreadyExistsException;
import fr.enssat.leave_manager.service.exception.not_found.TeamLeaderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamLeaderServiceImpl implements TeamLeaderService {
    @Autowired
    private TeamLeaderRepository repository;

    @Override
    public boolean exists(String id) {
        return repository.existsById(id);
    }

    @Override
    public TeamLeaderEntity getTeamLeader(String id) {
        return repository.findById(id).orElseThrow(() -> new TeamLeaderNotFoundException(id));
    }

    @Override
    public List<TeamLeaderEntity> getTeamLeaders() {
        return repository.findAll();
    }

    @Override
    public TeamLeaderEntity addTeamLeader(TeamLeaderEntity teamLeader) {
        if (exists(teamLeader.getEid()))
            throw new TeamLeaderAlreadyExistsException(teamLeader);
        return repository.saveAndFlush(teamLeader);
    }

    @Override
    public void deleteTeamLeader(String id) {
        repository.deleteById(id);
    }
}
