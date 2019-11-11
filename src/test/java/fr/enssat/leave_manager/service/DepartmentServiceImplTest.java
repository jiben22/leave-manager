package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.factory.DepartmentFactory;
import fr.enssat.leave_manager.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

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
