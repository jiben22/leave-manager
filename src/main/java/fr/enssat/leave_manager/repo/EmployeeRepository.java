package fr.enssat.leave_manager.repo;

import fr.enssat.leave_manager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
