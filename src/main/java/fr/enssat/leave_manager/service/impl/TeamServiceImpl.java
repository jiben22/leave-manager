package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.Team;
import fr.enssat.leave_manager.repository.TeamRepository;
import fr.enssat.leave_manager.service.TeamService;
import fr.enssat.leave_manager.service.exception.TeamNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository repository;

    @Override
    public Team getTeam(String id) {
        return repository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
    }

    @Override
    public List<Team> getTeamByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Team> getTeams() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public Team addTeam(Team team) {
        return repository.save(team);
    }

    @Override
    public Team editTeam(Team team) {
        return repository.save(team);
    }

    @Override
    public void deleteTeam(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteTeam(Team team) {
        repository.delete(team);
    }
}
