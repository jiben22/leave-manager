package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.factory.DepartmentFactory;
import fr.enssat.leave_manager.model.DepartmentEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository repository;

    @Test
    public void testGetDepartment() {
        Optional<DepartmentEntity> opt_dept = repository.findById("DEPARTMENT-157314099170606-0001");

        assertTrue(opt_dept.isPresent());

        DepartmentEntity dept = opt_dept.get();
        assertEquals(dept.getId(), "DEPARTMENT-157314099170606-0001");
        assertEquals(dept.getName(), "Space");
    }

    @Test
    public void testGetDepartmentException() {
        Optional<DepartmentEntity> opt_dept = repository.findById("Unknown ID");

        assertFalse(opt_dept.isPresent());
    }

    @Test
    public void testGetDepartmentByName() {
        Optional<DepartmentEntity> opt_dept = repository.findByName("Space");

        assertTrue(opt_dept.isPresent());

        DepartmentEntity dept = opt_dept.get();
        assertEquals(dept.getId(), "DEPARTMENT-157314099170606-0001");
        assertEquals(dept.getName(), "Space");
    }

    @Test
    public void testGetDepartmentByNameException() {
        Optional<DepartmentEntity> opt_dept = repository.findByName("Unknown");

        assertFalse(opt_dept.isPresent());
    }

    @Test
    public void testGetDepartments() {
        List<DepartmentEntity> depts = repository.findAll();

        assertNotNull(depts);
        assertNotEquals(depts.size(), 0);
    }

    @Test
    public void testSaveDepartment() {
        DepartmentEntity department = DepartmentFactory.getDepartment();
        DepartmentEntity added_department = repository.save(department);

        assertThat(department).isEqualToComparingFieldByField(added_department);
    }

    @Test
    public void testDeleteDepartmentById() {
        repository.deleteById("DEPARTMENT-157314099170606-0001");

        assertFalse(repository.existsById("DEPARTMENT-157314099170606-0001"));
    }
}
