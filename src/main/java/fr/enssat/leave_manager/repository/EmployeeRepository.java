package fr.enssat.leave_manager.repo;

import fr.enssat.leave_manager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Optional<Employee> findByFirstname(String firstname); // method automatically created
    Optional<Employee> findByLastname(String lastname); // method automatically created
    Optional<Employee> findByEmail(String email); // method automatically created
}
