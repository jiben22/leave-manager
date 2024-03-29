package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.TeamEntity;
import fr.enssat.leave_manager.repository.TeamRepository;
import fr.enssat.leave_manager.service.TeamService;
import fr.enssat.leave_manager.service.exception.already_exists.TeamAlreadyExistsException;
import fr.enssat.leave_manager.service.exception.not_found.TeamNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository repository;

    @Override
    public boolean exists(String id) {
        return repository.existsById(id);
    }

    @Override
    public TeamEntity getTeam(String id) {
        return repository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
    }

    @Override
    public List<TeamEntity> getTeamByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<TeamEntity> getTeams() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Secured("ROLE_HRD")
    @Override
    public TeamEntity addTeam(TeamEntity team) {
        if (exists(team.getId()))
            throw new TeamAlreadyExistsException(team);
        return repository.saveAndFlush(team);
    }

    @Secured("ROLE_HRD")
    @Override
    public TeamEntity editTeam(TeamEntity team) {
        if (!exists(team.getId()))
            throw new TeamNotFoundException(team.getId());
        return repository.saveAndFlush(team);
    }

    @Override
    public void deleteTeam(String id) {
        repository.deleteById(id);
    }
}
