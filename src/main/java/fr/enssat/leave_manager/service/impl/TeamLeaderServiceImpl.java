package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.TeamEntity;
import fr.enssat.leave_manager.model.TeamLeaderEntity;
import fr.enssat.leave_manager.repository.TeamLeaderRepository;
import fr.enssat.leave_manager.service.TeamLeaderService;
import fr.enssat.leave_manager.service.exception.already_exists.TeamLeaderAlreadyExistsException;
import fr.enssat.leave_manager.service.exception.not_found.TeamLeaderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Secured("ROLE_HRD")
    @Override
    public TeamLeaderEntity addTeamLeader(TeamLeaderEntity teamLeader) {
        if (exists(teamLeader.getEid()))
            throw new TeamLeaderAlreadyExistsException(teamLeader);
        return repository.saveAndFlush(teamLeader);
    }

    @Secured("ROLE_HRD")
    @Override
    public TeamLeaderEntity addEmployeeToTeamLeader(EmployeeEntity employee) {

        TeamLeaderEntity teamLeader =
                TeamLeaderEntity.builder()
                        .eid(employee.getEid())
                        .employee(employee)
                        .teamList(new HashSet<>())
                        .build();

        return repository.saveAndFlush(teamLeader);
    }

    @Override
    public void deleteTeamLeader(String id) {
        repository.deleteById(id);
    }
}
