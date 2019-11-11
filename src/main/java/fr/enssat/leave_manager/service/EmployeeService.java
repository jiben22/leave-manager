package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.Employee;
import fr.enssat.leave_manager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface EmployeeService {

    @Transactional
    Employee addEmployee(Employee employee);
}
