package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.LeaveManagerApplication;
import fr.enssat.leave_manager.factory.DepartmentFactory;
import fr.enssat.leave_manager.model.DepartmentEntity;
import fr.enssat.leave_manager.service.exception.DepartmentNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentServiceImplTest {
    @Mock
    private DepartmentServiceImpl departmentService;

    @Test
    public void testGetDepartment() {
        DepartmentEntity dept = departmentService.getDepartment("DEPARTMENT-157314099170606-0001");

        assertEquals(dept.getId(), "DEPARTMENT-157314099170606-0001");
        assertEquals(dept.getName(), "Space");
    }

    @Test(expected = DepartmentNotFoundException.class)
    public void testGetDepartmentException() {
        DepartmentEntity dept = departmentService.getDepartment("Unknown ID");
    }

    @Test
    public void testGetDepartmentByName() {
        DepartmentEntity dept = departmentService.getDepartmentByName("Space");

        assertEquals(dept.getId(), "DEPARTMENT-157314099170606-0001");
        assertEquals(dept.getName(), "Space");
    }

    @Test(expected = DepartmentNotFoundException.class)
    public void testGetDepartmentByNameException() {
        DepartmentEntity dept = departmentService.getDepartmentByName("Unknown");
    }

    @Test
    public void testGetDepartments() {
        List<DepartmentEntity> depts = departmentService.getDepartments();

        assertNotNull(depts);
        assertNotEquals(depts.size(), 0);
    }

    @Test
    public void testAddDepartment() {
        DepartmentEntity department = DepartmentFactory.getDepartment();
        DepartmentEntity added_department = departmentService.addDepartment(department);

        assertEquals(department, added_department);
    }

    @Test
    public void testEditDepartment() {
        DepartmentEntity department = departmentService.getDepartment("DEPARTMENT-157314099170606-0001");
        department.setName("Business");
        DepartmentEntity edited_department = departmentService.editDepartment(department);

        assertEquals(department, edited_department);
    }

    @Test
    public void testDeleteDepartmentById() {
        departmentService.deleteDepartment("DEPARTMENT-157314099170606-0001");

        assertFalse(departmentService.exists("DEPARTMENT-157314099170606-0001"));
    }

    @Test
    public void testDeleteDepartment() {
        DepartmentEntity dept = departmentService.getDepartment("DEPARTMENT-157314099170606-0002");
        departmentService.deleteDepartment(dept);

        assertFalse(departmentService.exists("DEPARTMENT-157314099170606-0002"));
    }
}
