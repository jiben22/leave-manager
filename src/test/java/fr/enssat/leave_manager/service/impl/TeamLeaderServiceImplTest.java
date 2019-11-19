package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.TeamLeaderFactory;
import fr.enssat.leave_manager.model.TeamLeaderEntity;
import fr.enssat.leave_manager.repository.TeamLeaderRepository;
import fr.enssat.leave_manager.service.exception.already_exists.TeamLeaderAlreadyExistsException;
import fr.enssat.leave_manager.service.exception.not_found.TeamLeaderNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TeamLeaderServiceImplTest {

    @Mock
    private TeamLeaderRepository repository;

    @InjectMocks
    private TeamLeaderServiceImpl teamLeaderService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetTeamLeader() {

        Optional<TeamLeaderEntity> teamLeader2 = TeamLeaderFactory.getTeamLeader2();

        when(repository.findById(teamLeader2.get().getEid()))
                .thenReturn(teamLeader2);

        TeamLeaderEntity teamLeader = teamLeaderService.getTeamLeader(teamLeader2.get().getEid());

        assertThat(teamLeader).isEqualToComparingFieldByField(teamLeader2.get());
    }

    @Test(expected = TeamLeaderNotFoundException.class)
    public void testGetTeamLeaderException() {

        when(repository.findById("Unknown ID")).thenThrow(TeamLeaderNotFoundException.class);
        teamLeaderService.getTeamLeader("Unknown ID");
    }

    @Test
    public void testGetTeamLeaders() {

        List<TeamLeaderEntity> teamLeaderList1 = new ArrayList<>();
        teamLeaderList1.add(TeamLeaderFactory.getTeamLeader2().get());
        teamLeaderList1.add(TeamLeaderFactory.getTeamLeader3().get());

        when(repository.findAll())
                .thenReturn(teamLeaderList1);

        List<TeamLeaderEntity> teamLeaderList = teamLeaderService.getTeamLeaders();

        assertEquals(teamLeaderList.size(), teamLeaderList1.size());
        assertThat(teamLeaderList.get(0)).isEqualToComparingFieldByField(teamLeaderList1.get(0));
        assertThat(teamLeaderList.get(1)).isEqualToComparingFieldByField(teamLeaderList1.get(1));
    }

    @Test
    public void testAddTeamLeader() {

        TeamLeaderEntity teamLeader2 = TeamLeaderFactory.getTeamLeader2().get();
        when(repository.saveAndFlush(teamLeader2))
                .thenReturn(teamLeader2);

        TeamLeaderEntity addedTeamLeader = teamLeaderService.addTeamLeader(teamLeader2);

        assertThat(addedTeamLeader).isEqualToComparingFieldByField(teamLeader2);
    }

    @Test(expected = TeamLeaderAlreadyExistsException.class)
    public void testAddTeamLeaderException() {

        TeamLeaderEntity teamLeader2 = TeamLeaderFactory.getTeamLeader2().get();

        when(repository.existsById(teamLeader2.getEid()))
                .thenThrow(TeamLeaderAlreadyExistsException.class);

        teamLeaderService.addTeamLeader(teamLeader2);
    }

//    @Test
//    public void testDeleteTeamLeader() {
//
//        teamLeaderService.deleteTeamLeader("EMPLOYEE-157314099170606-0002");
//        assertFalse(teamLeaderService.exists("EMPLOYEE-157314099170606-0002"));
//    }

    @Test(expected = TeamLeaderNotFoundException.class)
    public void testDeleteTeamLeaderException() {

        teamLeaderService.deleteTeamLeader("EMPLOYEE-157314099170606-9999");
    }
}
