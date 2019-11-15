package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.TeamLeaderFactory;
import fr.enssat.leave_manager.model.TeamLeaderEntity;
import fr.enssat.leave_manager.service.exception.TeamLeaderNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class TeamLeaderServiceImplTest {

    @Mock
    private TeamLeaderServiceImpl teamLeaderService;

    @Test
    public void testGetTeamLeader() {
        TeamLeaderEntity team_leader = teamLeaderService.getTeamLeader("EMPLOYEE-157314099170606-0005");

        assertEquals(team_leader.getEid(), "EMPLOYEE-157314099170606-0005");
    }

    @Test(expected = TeamLeaderNotFoundException.class)
    public void testGetTeamLeaderException() {
        TeamLeaderEntity team_leader = teamLeaderService.getTeamLeader("UNKNOWN ID");
    }

    @Test
    public void testGetTeamLeaders() {
        List<TeamLeaderEntity> team_leader_list = teamLeaderService.getTeamLeaders();

        assertNotNull(team_leader_list);
        assertNotEquals(team_leader_list.size(), 0);
    }

    @Test
    public void testAddTeamLeader() {
        TeamLeaderEntity team_leader = TeamLeaderFactory.getTeamLeader();
        TeamLeaderEntity added_team_leader = teamLeaderService.addTeamLeader(team_leader);

        assertEquals(team_leader, added_team_leader);
    }

    @Test
    public void testDeleteTeamLeader() {
        teamLeaderService.deleteTeamLeader("EMPLOYEE-157314099170606-0007");

        assertFalse(teamLeaderService.exists("EMPLOYEE-157314099170606-0007"));
    }
}
