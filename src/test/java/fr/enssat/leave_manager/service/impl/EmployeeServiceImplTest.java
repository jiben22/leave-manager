package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.EmployeeFactory;
import fr.enssat.leave_manager.model.EmployeeEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @Test
    public void testGetEmployee() {

        EmployeeEntity employee = EmployeeFactory.getEmployee();
    }
}
