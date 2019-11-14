package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findByFirstname(String firstname);
    List<Employee> findByLastname(String lastname);
    Optional<Employee> findByEmail(String email);
}
