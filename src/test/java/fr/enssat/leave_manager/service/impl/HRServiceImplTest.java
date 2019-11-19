package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.HRFactory;
import fr.enssat.leave_manager.model.HREntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class HRServiceImplTest {

    @Mock
    private HRServiceImpl hrService;

    @Test
    public void testGetHR() {

        HREntity hr = HRFactory.getHR();
    }
}