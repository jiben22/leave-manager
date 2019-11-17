package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.EmployeeFactory;
import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.service.exception.EmployeeNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {
    @Mock
    private EmployeeServiceImpl employeeService;

    @Test
    public void testGetEmployee() {
        EmployeeEntity employee = employeeService.getEmployee("EMPLOYEE-157314099170606-0001");

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
        assertTrue(employee.matchPassword("ironman"));
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void testGetEmployeeException() {
        EmployeeEntity employee = employeeService.getEmployee("UNKNOWN ID");
    }

    @Test
    public void testGetEmployeeByEmail() {
        EmployeeEntity employee = employeeService.getEmployeeByEmail("tony.stark@marvel.com");

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
        assertTrue(employee.matchPassword("ironman"));
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void testGetEmployeeByEmailException() {
        EmployeeEntity employee = employeeService.getEmployeeByEmail("UNKNOWN EMAIL");
    }

    @Test
    public void testGetEmployeeByFirstname() {
        List<EmployeeEntity> employee_list = employeeService.getEmployeeByFirstname("Tony");

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
        assertTrue(employee.matchPassword("ironman"));
    }

    @Test
    public void testGetEmployeeByLastname() {
        List<EmployeeEntity> employee_list = employeeService.getEmployeeByLastname("Stark");

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
        assertTrue(employee.matchPassword("ironman"));
    }

    @Test
    public void testGetEmployees() {
        List<EmployeeEntity> employee_list = employeeService.getEmployees();

        assertNotNull(employee_list);
        assertNotEquals(employee_list.size(), 0);
    }

    @Test
    public void testAddEmployee() {
        EmployeeEntity employee = EmployeeFactory.getEmployee();
        EmployeeEntity added_employe = employeeService.addEmployee(employee);

        assertEquals(employee, added_employe);
    }

    @Test
    public void testEditEmployee() {
        EmployeeEntity employee = employeeService.getEmployee("EMPLOYEE-157314099170606-0001");
        employee.setRemaining_leave(20.5);
        EmployeeEntity edited_employee = employeeService.editEmployee(employee);

        assertEquals(employee, edited_employee);
    }

    @Test
    public void testDeleteEmploye() {
        employeeService.deleteEmployee("EMPLOYEE-157314099170606-0004");

        assertFalse(employeeService.exists("EMPLOYEE-157314099170606-0004"));
    }
}
