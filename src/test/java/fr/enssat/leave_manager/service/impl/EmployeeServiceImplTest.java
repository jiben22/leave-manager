package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.EmployeeFactory;
import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.repository.EmployeeRepository;
import fr.enssat.leave_manager.service.exception.not_found.EmployeeNotFoundException;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetEmployee() {
        when(repository.findById("EMPLOYEE-157314099170606-0002"))
                .thenReturn(EmployeeFactory.getEmployee2());

        EmployeeEntity employee = employeeService.getEmployee("EMPLOYEE-157314099170606-0002");

        assertEquals(employee.getEid(), "EMPLOYEE-157314099170606-0002");
        assertEquals(employee.getFirstname(), "Thor");
        assertEquals(employee.getLastname(), "Odinson");
        assertEquals(employee.getStreet(), "5 avenue Asgardian");
        assertEquals(employee.getPostCode(), "22700");
        assertEquals(employee.getCity(), "Lannion");
        assertEquals(employee.getCountry(), "France");
        assertEquals(employee.getEmail(), "thor@marvel.com");
        assertEquals(employee.getPosition(), "God");
        assertTrue(employee.matchesPassword("Thor56789*"));
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void testGetEmployeeException() {
        when(repository.findById("Unknown ID")).thenThrow(EmployeeNotFoundException.class);
        employeeService.getEmployee("Unknown ID");
    }

    @Test
    public void testGetEmployeeByEmail() {
        when(repository.findByEmail("thor@marvel.com")).thenReturn(EmployeeFactory.getEmployee2());

        EmployeeEntity employee = employeeService.getEmployeeByEmail("thor@marvel.com");

        assertEquals(employee.getEid(), "EMPLOYEE-157314099170606-0002");
        assertEquals(employee.getFirstname(), "Thor");
        assertEquals(employee.getLastname(), "Odinson");
        assertEquals(employee.getStreet(), "5 avenue Asgardian");
        assertEquals(employee.getPostCode(), "22700");
        assertEquals(employee.getCity(), "Lannion");
        assertEquals(employee.getCountry(), "France");
        assertEquals(employee.getEmail(), "thor@marvel.com");
        assertEquals(employee.getPosition(), "God");
        assertTrue(employee.matchesPassword("Thor56789*"));
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void testGetEmployeeByEmailException() {
        when(repository.findByEmail("Unknown email")).thenThrow(EmployeeNotFoundException.class);
        employeeService.getEmployeeByEmail("Unknown email");
    }

    @Test
    public void testGetEmployeeByFirstname() {
        List<EmployeeEntity> list = new ArrayList<>();
        list.add(EmployeeFactory.getEmployee());
        when(repository.findByFirstname("Captain")).thenReturn(list);
        EmployeeEntity employeeFactory = list.get(0);

        List<EmployeeEntity> listEmployeeRepo = employeeService.getEmployeeByFirstname("Captain");

        assertNotNull(listEmployeeRepo);
        assertNotEquals(listEmployeeRepo.size(), 0);

        EmployeeEntity employeeRepo = listEmployeeRepo.get(0);

        assertEquals(employeeRepo.getEid(), employeeFactory.getEid());
        assertEquals(employeeRepo.getFirstname(), employeeFactory.getFirstname());
        assertEquals(employeeRepo.getLastname(), employeeFactory.getLastname());
        assertEquals(employeeRepo.getStreet(), employeeFactory.getStreet());
        assertEquals(employeeRepo.getPostCode(), employeeFactory.getPostCode());
        assertEquals(employeeRepo.getCity(), employeeFactory.getCity());
        assertEquals(employeeRepo.getCountry(), employeeFactory.getCountry());
        assertEquals(employeeRepo.getEmail(), employeeFactory.getEmail());
        assertEquals(employeeRepo.getPosition(), employeeFactory.getPosition());
        assertTrue(employeeRepo.matchesPassword("@Password99"));
    }

    @Test
    public void testGetEmployeeByLastname() {
        List<EmployeeEntity> list = new ArrayList<>();
        list.add(EmployeeFactory.getEmployee());
        when(repository.findByLastname("America")).thenReturn(list);
        EmployeeEntity employeeFactory = list.get(0);

        List<EmployeeEntity> listEmployeeRepo = employeeService.getEmployeeByLastname("America");

        assertNotNull(listEmployeeRepo);
        assertNotEquals(listEmployeeRepo.size(), 0);

        EmployeeEntity employeeRepo = listEmployeeRepo.get(0);

        assertEquals(employeeRepo.getEid(), employeeFactory.getEid());
        assertEquals(employeeRepo.getFirstname(), employeeFactory.getFirstname());
        assertEquals(employeeRepo.getLastname(), employeeFactory.getLastname());
        assertEquals(employeeRepo.getStreet(), employeeFactory.getStreet());
        assertEquals(employeeRepo.getPostCode(), employeeFactory.getPostCode());
        assertEquals(employeeRepo.getCity(), employeeFactory.getCity());
        assertEquals(employeeRepo.getCountry(), employeeFactory.getCountry());
        assertEquals(employeeRepo.getEmail(), employeeFactory.getEmail());
        assertEquals(employeeRepo.getPosition(), employeeFactory.getPosition());
        assertTrue(employeeRepo.matchesPassword("@Password99"));
    }

    @Test
    public void testGetEmployees() {
        List<EmployeeEntity> list = new ArrayList<>();
        list.add(EmployeeFactory.getEmployee());
        when(repository.findAll(Sort.by(Sort.Direction.ASC, "lastname"))).thenReturn(list);

        EmployeeEntity employeeFactory = list.get(0);

        List<EmployeeEntity> employeeList = employeeService.getEmployees();

        assertNotNull(employeeList);
        assertNotEquals(employeeList.size(), 0);

        EmployeeEntity employeeRepo = employeeList.get(0);

        assertEquals(employeeRepo.getEid(), employeeFactory.getEid());
        assertEquals(employeeRepo.getFirstname(), employeeFactory.getFirstname());
        assertEquals(employeeRepo.getLastname(), employeeFactory.getLastname());
        assertEquals(employeeRepo.getStreet(), employeeFactory.getStreet());
        assertEquals(employeeRepo.getPostCode(), employeeFactory.getPostCode());
        assertEquals(employeeRepo.getCity(), employeeFactory.getCity());
        assertEquals(employeeRepo.getCountry(), employeeFactory.getCountry());
        assertEquals(employeeRepo.getEmail(), employeeFactory.getEmail());
        assertEquals(employeeRepo.getPosition(), employeeFactory.getPosition());
        assertTrue(employeeRepo.matchesPassword("@Password99"));

        List<EmployeeEntity> employee_list = employeeService.getEmployees();
    }

    @Test
    public void testAddEmployee() {
        EmployeeEntity employeeFactory = EmployeeFactory.getEmployee();

        when(repository.saveAndFlush(employeeFactory)).thenReturn(employeeFactory);

        EmployeeEntity employeeRepo = employeeService.addEmployee(employeeFactory);

        assertEquals(employeeRepo.getEid(), employeeFactory.getEid());
        assertEquals(employeeRepo.getFirstname(), employeeFactory.getFirstname());
        assertEquals(employeeRepo.getLastname(), employeeFactory.getLastname());
        assertEquals(employeeRepo.getStreet(), employeeFactory.getStreet());
        assertEquals(employeeRepo.getPostCode(), employeeFactory.getPostCode());
        assertEquals(employeeRepo.getCity(), employeeFactory.getCity());
        assertEquals(employeeRepo.getCountry(), employeeFactory.getCountry());
        assertEquals(employeeRepo.getEmail(), employeeFactory.getEmail());
        assertEquals(employeeRepo.getPosition(), employeeFactory.getPosition());
        assertTrue(employeeRepo.matchesPassword("@Password99"));
    }

    @Test
    public void testEditEmployee() {
        EmployeeEntity employeeFactory = EmployeeFactory.getEmployee1();

        employeeFactory.setRemainingLeave(20.5);

        when(repository.saveAndFlush(employeeFactory)).thenReturn(employeeFactory);
        when(repository.existsById(employeeFactory.getEid())).thenReturn(true);

        EmployeeEntity employeeRepo = employeeService.editEmployee(employeeFactory);

        assertEquals(employeeRepo.getEid(), employeeFactory.getEid());
        assertEquals(employeeRepo.getFirstname(), employeeFactory.getFirstname());
        assertEquals(employeeRepo.getLastname(), employeeFactory.getLastname());
        assertEquals(employeeRepo.getStreet(), employeeFactory.getStreet());
        assertEquals(employeeRepo.getPostCode(), employeeFactory.getPostCode());
        assertEquals(employeeRepo.getCity(), employeeFactory.getCity());
        assertEquals(employeeRepo.getCountry(), employeeFactory.getCountry());
        assertEquals(employeeRepo.getEmail(), employeeFactory.getEmail());
        assertEquals(employeeRepo.getPosition(), employeeFactory.getPosition());
        assertTrue(employeeRepo.matchesPassword("Ironman12*"));
    }

    @Test
    public void testDeleteEmploye() {
        employeeService.deleteEmployee("EMPLOYEE-157314099170606-0004");

        assertFalse(employeeService.exists("EMPLOYEE-157314099170606-0004"));
    }
}
