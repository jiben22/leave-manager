package fr.enssat.leave_manager.repo;

import fr.enssat.leave_manager.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    Optional<Department> findByName(String name); // method automatically created
}
