package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.factory.HRFactory;
import fr.enssat.leave_manager.model.HREntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HRRepositoryTest {
    @Autowired
    private HRRepository repository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetHR() {
        Optional<HREntity> opt_hr = repository.findById("EMPLOYEE-157314099170606-0006");

        assertTrue(opt_hr.isPresent());

        HREntity hr = opt_hr.get();

        assertEquals(hr.getEid(), "EMPLOYEE-157314099170606-0006");
    }

    @Test
    public void testGetHRException() {
        Optional<HREntity> opt_hrd = repository.findById("UNKNOWN ID");

        assertFalse(opt_hrd.isPresent());
    }

    @Test
    public void testGetHRs() {
        List<HREntity> hrd_list = repository.findAll();

        assertNotNull(hrd_list);
        assertNotEquals(hrd_list.size(), 0);
    }

    @Test
    public void testSaveHR() {
        HREntity hrd = HRFactory.getHR10();
        HREntity added_hrd= repository.saveAndFlush(hrd);

        assertEquals(hrd.getEid(), added_hrd.getEid());

        assertTrue(employeeRepository.existsById(hrd.getEid()));
    }

    @Test
    public void testDeleteHR() {
        repository.deleteById("EMPLOYEE-157314099170606-0006");

        assertFalse(repository.existsById("EMPLOYEE-157314099170606-0006"));
    }
}
