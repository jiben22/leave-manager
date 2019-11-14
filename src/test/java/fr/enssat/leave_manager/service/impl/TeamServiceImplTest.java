package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.TeamFactory;
import fr.enssat.leave_manager.model.Team;
import fr.enssat.leave_manager.service.impl.TeamServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TeamServiceImplTest {

    @Mock
    private TeamServiceImpl teamService;

    @Test
    public void testGetTeam() {

        Team team = TeamFactory.getTeam();
    }
}
