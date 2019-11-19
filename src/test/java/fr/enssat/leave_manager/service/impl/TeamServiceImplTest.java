package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.TeamFactory;
import fr.enssat.leave_manager.model.TeamEntity;
import fr.enssat.leave_manager.service.exception.not_found.TeamNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class TeamServiceImplTest {
//
//    @Mock
//    private TeamServiceImpl teamService;
//
    @Test
    public void testGetTeam() {
//        TeamEntity team = teamService.getTeam("TEAM-157314099170606-0001");
//
//        assertEquals(team.getId(), "TEAM-157314099170606-0001");
//        assertEquals(team.getName(), "Stark corporation");
    }
//
//    @Test(expected = TeamNotFoundException.class)
//    public void testGetTeamException() {
//        TeamEntity team = teamService.getTeam("UNKNOWN ID");
//    }
//
//    @Test
//    public void testGetTeamByName() {
//        List<TeamEntity> team_list = teamService.getTeamByName("Stark corporation");
//
//        assertNotNull(team_list);
//        assertNotEquals(team_list.size(), 0);
//
//        TeamEntity team = team_list.get(0);
//
//        assertEquals(team.getId(), "TEAM-157314099170606-0001");
//        assertEquals(team.getName(), "Stark corporation");
//    }
//
//    @Test
//    public void testGetTeams() {
//        List<TeamEntity> team_list = teamService.getTeams();
//
//        assertNotNull(team_list);
//        assertNotEquals(team_list.size(), 0);
//    }
//
//    @Test
//    public void testAddTeams() {
//        TeamEntity team = TeamFactory.getTeam();
//        TeamEntity added_team = teamService.addTeam(team);
//
//        assertEquals(team, added_team);
//    }
//
//    @Test
//    public void testEditTeams() {
//        TeamEntity team = teamService.getTeam("TEAM-157314099170606-0001");
//        team.setName("New Name Corporation");
//        TeamEntity edited_team = teamService.editTeam(team);
//
//        assertEquals(team.getId(), "TEAM-157314099170606-0001");
//        assertEquals(team.getName(), "New Name Corporation");
//    }
//
//    @Test
//    public void testDeleteTeam() {
//        teamService.deleteTeam("TEAM-157314099170606-0001");
//
//        assertFalse(teamService.exists("TEAM-157314099170606-0001"));
//    }
}
