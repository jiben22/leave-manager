package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.TeamLeader;
import fr.enssat.leave_manager.repository.TeamLeaderRepository;
import fr.enssat.leave_manager.service.TeamLeaderService;
import fr.enssat.leave_manager.service.exception.TeamLeaderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamLeaderServiceImpl implements TeamLeaderService {
    @Autowired
    private TeamLeaderRepository repository;

    @Override
    public TeamLeader getTeamLeader(String id) {
        return repository.findById(id).orElseThrow(() -> new TeamLeaderNotFoundException(id));
    }

    @Override
    public List<TeamLeader> getTeamLeaders() {
        return repository.findAll();
    }

    @Override
    public TeamLeader addTeamLeader(TeamLeader teamLeader) {
        return repository.save(teamLeader);
    }

    @Override
    public TeamLeader editTeamLeader(TeamLeader teamLeader) {
        return repository.save(teamLeader);
    }

    @Override
    public void deleteTeamLeader(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteTeamLeader(TeamLeader teamLeader) {
        repository.delete(teamLeader);
    }
}
