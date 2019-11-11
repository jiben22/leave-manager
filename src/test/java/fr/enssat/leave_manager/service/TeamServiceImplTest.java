package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.factory.DepartmentFactory;
import fr.enssat.leave_manager.factory.TeamFactory;
import fr.enssat.leave_manager.model.Department;
import fr.enssat.leave_manager.model.Team;
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
