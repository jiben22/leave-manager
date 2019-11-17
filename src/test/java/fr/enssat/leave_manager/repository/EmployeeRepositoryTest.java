package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.factory.EmployeeFactory;
import fr.enssat.leave_manager.model.EmployeeEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository repository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    public void testGetEmployee() {
        Optional<EmployeeEntity> opt_employee = repository.findById("EMPLOYEE-157314099170606-0001");

        assertTrue(opt_employee.isPresent());

        EmployeeEntity employee = opt_employee.get();

        assertEquals(employee.getEid(), "EMPLOYEE-157314099170606-0001");
        assertEquals(employee.getFirstname(), "Tony");
        assertEquals(employee.getLastname(), "Stark");
        assertEquals(employee.getStreet(), "9 rue du chene germain");
        assertEquals(employee.getPost_code(), "22700");
        assertEquals(employee.getCity(), "Lannion");
        assertEquals(employee.getCountry(), "France");
        assertEquals(employee.getRemaining_leave(), 25.0);
        assertEquals(employee.getEmail(), "tony.stark@marvel.com");
        assertEquals(employee.getPosition(), "Director");
        assertTrue(encoder.matches("ironman", employee.getPassword()));
    }

    @Test
    public void testGetEmployeeException() {
        Optional<EmployeeEntity> opt_employee = repository.findById("UNKNOWN ID");

        assertFalse(opt_employee.isPresent());
    }

    @Test
    public void testGetEmployeeByEmail() {
        Optional<EmployeeEntity> opt_employee = repository.findByEmail("tony.stark@marvel.com");

        assertTrue(opt_employee.isPresent());

        EmployeeEntity employee = opt_employee.get();

        assertEquals(employee.getEid(), "EMPLOYEE-157314099170606-0001");
        assertEquals(employee.getFirstname(), "Tony");
        assertEquals(employee.getLastname(), "Stark");
        assertEquals(employee.getStreet(), "9 rue du chene germain");
        assertEquals(employee.getPost_code(), "22700");
        assertEquals(employee.getCity(), "Lannion");
        assertEquals(employee.getCountry(), "France");
        assertEquals(employee.getRemaining_leave(), 25.0);
        assertEquals(employee.getEmail(), "tony.stark@marvel.com");
        assertEquals(employee.getPosition(), "Director");
        assertTrue(encoder.matches("ironman", employee.getPassword()));
    }

    @Test
    public void testGetEmployeeByEmailException() {
        Optional<EmployeeEntity> opt_employee = repository.findByEmail("unknown");

        assertFalse(opt_employee.isPresent());
    }

    @Test
    public void testGetEmployeeByFirstname() {
        List<EmployeeEntity> employee_list = repository.findByFirstname("Tony");

        assertNotNull(employee_list);
        assertNotEquals(employee_list.size(), 0);

        EmployeeEntity employee = employee_list.get(0);

        assertEquals(employee.getEid(), "EMPLOYEE-157314099170606-0001");
        assertEquals(employee.getFirstname(), "Tony");
        assertEquals(employee.getLastname(), "Stark");
        assertEquals(employee.getStreet(), "9 rue du chene germain");
        assertEquals(employee.getPost_code(), "22700");
        assertEquals(employee.getCity(), "Lannion");
        assertEquals(employee.getCountry(), "France");
        assertEquals(employee.getRemaining_leave(), 25.0);
        assertEquals(employee.getEmail(), "tony.stark@marvel.com");
        assertEquals(employee.getPosition(), "Director");
        assertTrue(encoder.matches("ironman", employee.getPassword()));
    }

    @Test
    public void testGetEmployeeByLastname() {
        List<EmployeeEntity> employee_list = repository.findByLastname("Stark");

        assertNotNull(employee_list);
        assertNotEquals(employee_list.size(), 0);

        EmployeeEntity employee = employee_list.get(0);

        assertEquals(employee.getEid(), "EMPLOYEE-157314099170606-0001");
        assertEquals(employee.getFirstname(), "Tony");
        assertEquals(employee.getLastname(), "Stark");
        assertEquals(employee.getStreet(), "9 rue du chene germain");
        assertEquals(employee.getPost_code(), "22700");
        assertEquals(employee.getCity(), "Lannion");
        assertEquals(employee.getCountry(), "France");
        assertEquals(employee.getRemaining_leave(), 25.0);
        assertEquals(employee.getEmail(), "tony.stark@marvel.com");
        assertEquals(employee.getPosition(), "Director");
        assertTrue(encoder.matches("ironman", employee.getPassword()));
    }

    @Test
    public void testGetEmployees() {
        List<EmployeeEntity> employee_list = repository.findAll();

        assertNotNull(employee_list);
        assertNotEquals(employee_list.size(), 0);
    }

    @Test
    public void testSaveEmployee() {
        EmployeeEntity employee = EmployeeFactory.getEmployee();
        EmployeeEntity added_employe = repository.save(employee);

        assertThat(employee).isEqualToComparingFieldByField(added_employe);
    }

    @Test
    public void testDeleteEmployeeById() {
        repository.deleteById("EMPLOYEE-157314099170606-0008");

        assertFalse(repository.existsById("EMPLOYEE-157314099170606-0008"));
    }
}
