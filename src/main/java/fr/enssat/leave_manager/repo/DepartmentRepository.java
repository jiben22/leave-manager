package fr.enssat.leave_manager.repo;

import fr.enssat.leave_manager.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
