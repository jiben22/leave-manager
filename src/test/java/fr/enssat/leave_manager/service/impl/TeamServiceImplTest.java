package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.TeamFactory;
import fr.enssat.leave_manager.model.TeamEntity;
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

        TeamEntity team = TeamFactory.getTeam();
    }
}
