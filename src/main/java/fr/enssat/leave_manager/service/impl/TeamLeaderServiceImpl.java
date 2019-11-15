package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.TeamLeaderEntity;
import fr.enssat.leave_manager.repository.TeamLeaderRepository;
import fr.enssat.leave_manager.service.TeamLeaderService;
import fr.enssat.leave_manager.service.exception.TeamLeaderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamLeaderServiceImpl implements TeamLeaderService {

    private final TeamLeaderRepository repository;

    public TeamLeaderServiceImpl(TeamLeaderRepository repository) {
        this.repository = repository;
    }

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
        return repository.save(teamLeader);
    }

    @Override
    public void deleteTeamLeader(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteTeamLeader(TeamLeaderEntity teamLeader) {
        repository.delete(teamLeader);
    }
}
