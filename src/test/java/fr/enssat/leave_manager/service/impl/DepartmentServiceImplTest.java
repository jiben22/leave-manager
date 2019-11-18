package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.DepartmentFactory;
import fr.enssat.leave_manager.model.DepartmentEntity;
import fr.enssat.leave_manager.repository.DepartmentRepository;
import fr.enssat.leave_manager.service.exception.DepartmentNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class DepartmentServiceImplTest {
    @Mock
    private DepartmentRepository repository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetDepartment() {
        when(repository.findById("DEPARTMENT-157314099170606-0001"))
                .thenReturn(DepartmentFactory.getDepartment1());

        DepartmentEntity dept = departmentService.getDepartment("DEPARTMENT-157314099170606-0001");

        assertEquals(dept.getId(), "DEPARTMENT-157314099170606-0001");
        assertEquals(dept.getName(), "Space");
    }

    @Test(expected = DepartmentNotFoundException.class)
    public void testGetDepartmentException() {
        when(repository.findById("Unknown ID")).thenThrow(DepartmentNotFoundException.class);
        DepartmentEntity dept = departmentService.getDepartment("Unknown ID");
    }

    @Test
    public void testGetDepartmentByName() {
        when(repository.findByName("Space"))
                .thenReturn(DepartmentFactory.getDepartment1());

        DepartmentEntity dept = departmentService.getDepartmentByName("Space");

        assertEquals(dept.getId(), "DEPARTMENT-157314099170606-0001");
        assertEquals(dept.getName(), "Space");
    }

    @Test(expected = DepartmentNotFoundException.class)
    public void testGetDepartmentByNameException() {
        when(repository.findByName("Unknown")).thenThrow(DepartmentNotFoundException.class);
        DepartmentEntity dept = departmentService.getDepartmentByName("Unknown");
    }

    @Test
    public void testGetDepartments() {
        List<DepartmentEntity> list = new ArrayList<DepartmentEntity>();
        list.add(DepartmentFactory.getDepartment());

        when(repository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(list);

        List<DepartmentEntity> depts = departmentService.getDepartments();

        assertNotNull(depts);
        assertNotEquals(depts.size(), 0);
    }

    @Test
    public void testAddDepartment() {
        DepartmentEntity department = DepartmentFactory.getDepartment();
        when(repository.save(department))
                .thenReturn(department);

        DepartmentEntity added_department = departmentService.addDepartment(department);

        assertEquals(department, added_department);
    }

    @Test
    public void testEditDepartment() {
        DepartmentEntity department = DepartmentFactory.getDepartment();
        department.setName("Business");

        when(repository.save(department))
                .thenReturn(department);

        DepartmentEntity edited_department = departmentService.editDepartment(department);

        assertEquals(department, edited_department);
    }

    @Test
    public void testDeleteDepartment() {
        departmentService.deleteDepartment("DEPARTMENT-157314099170606-0001");

        assertFalse(departmentService.exists("DEPARTMENT-157314099170606-0001"));
    }
}
