package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Optional<Employee> findByFirstname(String firstname); // method automatically created
    Optional<Employee> findByLastname(String lastname); // method automatically created
    Optional<Employee> findByEmail(String email); // method automatically created
}
