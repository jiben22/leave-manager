package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.DepartmentFactory;
import fr.enssat.leave_manager.model.DepartmentEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DepartmentServiceImplTest {

    @Mock
    private DepartmentServiceImpl departmentService;

    @Test
    public void testAddDepartment() {

        DepartmentEntity department = DepartmentFactory.getDepartment();
        DepartmentEntity departmentAdded = departmentService.addDepartment(department);
    }
}
