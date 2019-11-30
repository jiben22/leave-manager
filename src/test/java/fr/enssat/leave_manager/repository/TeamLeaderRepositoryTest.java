package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.factory.TeamLeaderFactory;
import fr.enssat.leave_manager.model.TeamLeaderEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TeamLeaderRepositoryTest {
    @Autowired
    private TeamLeaderRepository repository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetTeamLeader() {
        Optional<TeamLeaderEntity> opt_team_leader = repository.findById("EMPLOYEE-157314099170606-0005");

        assertTrue(opt_team_leader.isPresent());

        TeamLeaderEntity team_leader = opt_team_leader.get();

        assertEquals(team_leader.getEid(), "EMPLOYEE-157314099170606-0005");
    }

    @Test
    public void testGetTeamLeaderException() {
        Optional<TeamLeaderEntity> opt_team_leader = repository.findById("UNKNOWN ID");

        assertFalse(opt_team_leader.isPresent());
    }

    @Test
    public void testGetTeamLeaders() {
        List<TeamLeaderEntity> team_leader_list = repository.findAll();

        assertNotNull(team_leader_list);
        assertNotEquals(team_leader_list.size(), 0);
    }

    @Test
    public void testSaveTeamLeader() {
        TeamLeaderEntity team_leader = TeamLeaderFactory.getTeamLeader3().get();
        TeamLeaderEntity added_team_leader = repository.saveAndFlush(team_leader);

        assertEquals(team_leader.getEid(), added_team_leader.getEid());

        assertTrue(employeeRepository.existsById(team_leader.getEid()));
    }
/*
    @Test
    public void testDeleteTeamLeader() {
        repository.deleteById("EMPLOYEE-157314099170606-0007");

        assertFalse(repository.existsById("EMPLOYEE-157314099170606-0007"));
    }*/
}
