package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.TeamLeaderFactory;
import fr.enssat.leave_manager.model.TeamLeader;
import fr.enssat.leave_manager.service.impl.TeamLeaderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TeamLeaderServiceImplTest {

    @Mock
    private TeamLeaderServiceImpl teamLeaderService;

    @Test
    public void testGetTeamLeader() {

        TeamLeader teamLeader = TeamLeaderFactory.getTeamLeader();
    }
}
