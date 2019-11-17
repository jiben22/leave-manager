package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.factory.HRDFactory;
import fr.enssat.leave_manager.model.HRDEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HRDRepositoryTest {
    @Autowired
    private HRDRepository repository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetHRD() {
        Optional<HRDEntity> opt_hrd = repository.findById("EMPLOYEE-157314099170606-0002");

        assertTrue(opt_hrd.isPresent());

        HRDEntity hrd = opt_hrd.get();

        assertEquals(hrd.getEid(), "EMPLOYEE-157314099170606-0002");
    }

    @Test
    public void testGetHRDException() {
        Optional<HRDEntity> opt_hrd = repository.findById("UNKNOWN ID");

        assertFalse(opt_hrd.isPresent());
    }

    @Test
    public void testGetHRDs() {
        List<HRDEntity> hrd_list = repository.findAll();

        assertNotNull(hrd_list);
        assertNotEquals(hrd_list.size(), 0);
    }

    @Test
    public void testSaveHRD() {
        HRDEntity hrd = HRDFactory.getHRD5();
        HRDEntity added_hrd= repository.saveAndFlush(hrd);

        assertEquals(hrd.getEid(), added_hrd.getEid());

        assertTrue(employeeRepository.existsById(hrd.getEid()));
    }

    @Test
    public void testDeleteHRD() {
        repository.deleteById("EMPLOYEE-157314099170606-0002");

        assertFalse(repository.existsById("EMPLOYEE-157314099170606-0002"));
    }
}
