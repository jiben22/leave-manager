package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.factory.TeamFactory;
import fr.enssat.leave_manager.model.TeamEntity;
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
public class TeamRepositoryTest {
    @Autowired
    private TeamRepository repository;

    @Test
    public void testGetTeam() {
        Optional<TeamEntity> opt_team = repository.findById("TEAM-157314099170606-0001");

        assertTrue(opt_team.isPresent());

        TeamEntity team = opt_team.get();

        assertEquals(team.getId(), "TEAM-157314099170606-0001");
        assertEquals(team.getName(), "Stark corporation");
    }

    @Test
    public void testGetTeamException() {
        Optional<TeamEntity> opt_team = repository.findById("UNKNOWN ID");

        assertFalse(opt_team.isPresent());
    }

    @Test
    public void testGetTeamByName() {
        List<TeamEntity> team_list = repository.findByName("Stark corporation");

        assertNotNull(team_list);
        assertNotEquals(team_list.size(), 0);

        TeamEntity team = team_list.get(0);

        assertEquals(team.getId(), "TEAM-157314099170606-0001");
        assertEquals(team.getName(), "Stark corporation");
    }

    @Test
    public void testGetTeams() {
        List<TeamEntity> team_list = repository.findAll();

        assertNotNull(team_list);
        assertNotEquals(team_list.size(), 0);
    }

    @Test
    public void testSaveTeam() {
        TeamEntity team = TeamFactory.getTeam();
        TeamEntity added_team = repository.saveAndFlush(team);

        assertEquals(team.getId(), added_team.getId());
        assertEquals(team.getName(), added_team.getName());
    }

    @Test
    public void testDeleteTeam() {
        repository.deleteById("TEAM-157314099170606-0001");

        assertFalse(repository.existsById("TEAM-157314099170606-0001"));
    }
}
