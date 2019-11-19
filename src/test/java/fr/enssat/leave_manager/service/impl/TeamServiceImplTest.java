package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.TeamFactory;
import fr.enssat.leave_manager.model.TeamEntity;
import fr.enssat.leave_manager.repository.TeamRepository;
import fr.enssat.leave_manager.service.exception.not_found.TeamNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TeamServiceImplTest {

    @Mock
    private TeamRepository repository;

    @InjectMocks
    private TeamServiceImpl teamService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetTeam() {

        Optional<TeamEntity> team2 = TeamFactory.getTeam2();

        when(repository.findById(team2.get().getId()))
                .thenReturn(team2);

        TeamEntity team = teamService.getTeam(team2.get().getId());

        assertThat(team).isEqualToComparingFieldByField(team2.get());
    }

    @Test(expected = TeamNotFoundException.class)
    public void testGetTeamException() {

        when(repository.findById("Unknown ID"))
                .thenThrow(TeamNotFoundException.class);

        teamService.getTeam("Unknown ID");
    }

    @Test
    public void testGetTeamByName() {

        List<TeamEntity> teamList1 = new ArrayList<>();
        teamList1.add(TeamFactory.getTeam2().get());
        teamList1.add(TeamFactory.getTeam3().get());

        when(repository.findByName("Bifrost traveler"))
                .thenReturn(teamList1);

        List<TeamEntity> teamList = teamService.getTeamByName("Bifrost traveler");

        assertEquals(teamList.size(), teamList1.size());
        assertThat(teamList.get(0)).isEqualToComparingFieldByField(teamList1.get(0));
        assertThat(teamList.get(1)).isEqualToComparingFieldByField(teamList1.get(1));
    }

    @Test
    public void testGetTeams() {

        List<TeamEntity> teamList1 = new ArrayList<>();
        // Add teams with DESC sorting
        teamList1.add(TeamFactory.getTeam3().get());
        teamList1.add(TeamFactory.getTeam2().get());

        when(repository.findAll(Sort.by(Sort.Direction.ASC, "name")))
                .thenReturn(teamList1);

        List<TeamEntity> teamList = teamService.getTeams();

        assertEquals(teamList.size(), teamList1.size());
        assertThat(teamList.get(0)).isEqualToComparingFieldByField(teamList1.get(0));
        assertThat(teamList.get(1)).isEqualToComparingFieldByField(teamList1.get(1));
    }

    @Test
    public void testAddTeam() {

        TeamEntity team = TeamFactory.getTeam2().get();
        when(repository.saveAndFlush(team))
                .thenReturn(team);

        TeamEntity addedTeam = teamService.addTeam(team);

        assertThat(addedTeam).isEqualToComparingFieldByField(team);
    }

    @Test
    public void testEditTeam() {

        TeamEntity team = TeamFactory.getTeam2().get();
        team.setName("Name");

        when(repository.saveAndFlush(team))
                .thenReturn(team);

        TeamEntity editedTeam = teamService.editTeam(team);

        assertThat(editedTeam).isEqualToComparingFieldByField(team);
    }

    @Test
    public void testDeleteTeam() {

        TeamEntity team = TeamFactory.getTeam2().get();
        teamService.deleteTeam(team.getId());
    }
}
