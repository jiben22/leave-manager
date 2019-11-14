package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.Employee;
import fr.enssat.leave_manager.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface EmployeeService {
    Employee getEmployee(String id);
    Employee getEmployeeByEmail(String email);
    List<Employee> getEmployeeByFirstname(String firstname);
    List<Employee> getEmployeeByLastname(String lastname);
    List<Employee> getEmployees();
    Employee addEmployee(Employee employee);
    Employee editEmployee(Employee employee);
    void deleteEmployee(String id);
    void deleteEmployee(Employee employee);
}
