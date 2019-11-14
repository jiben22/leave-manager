package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.DepartmentFactory;
import fr.enssat.leave_manager.model.Department;
import fr.enssat.leave_manager.service.impl.DepartmentServiceImpl;
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

        Department department = DepartmentFactory.getDepartment();
        Department departmentAdded = departmentService.addDepartment(department);
    }
}
