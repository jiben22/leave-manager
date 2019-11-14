package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.factory.HRDFactory;
import fr.enssat.leave_manager.model.HRD;
import fr.enssat.leave_manager.service.impl.HRDServiceImpl;
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

        HRD hrd = HRDFactory.getHRD();
    }
}
