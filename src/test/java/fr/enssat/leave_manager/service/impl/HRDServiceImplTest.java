package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.HRDFactory;
import fr.enssat.leave_manager.model.HRDEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class HRDServiceImplTest {

    @Mock
    private HRDServiceImpl hrdService;

    @Test
    public void testGetHRD() {

        HRDEntity hrd = HRDFactory.getHRD();
    }
}
