package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.EmployeeEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface EmployeeService {
    boolean exists(String id);
    EmployeeEntity getEmployee(String id);
    EmployeeEntity getEmployeeByEmail(String email);
    List<EmployeeEntity> getEmployeeByFirstname(String firstname);
    List<EmployeeEntity> getEmployeeByLastname(String lastname);
    List<EmployeeEntity> getEmployees();
    EmployeeEntity addEmployee(EmployeeEntity employee);
    EmployeeEntity editEmployee(EmployeeEntity employee);
    void deleteEmployee(String id);
}