package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.TypeOfLeaveFactory;
import fr.enssat.leave_manager.model.TypeOfLeaveEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TypeOfLeaveServiceImplTest {

    @Mock
    private TypeOfLeaveServiceImpl typeOfLeaveService;

    @Test
    public void testGetTypeOfLeave() {

        TypeOfLeaveEntity typeOfLeave = TypeOfLeaveFactory.getTypeOfLeave();
    }
}
