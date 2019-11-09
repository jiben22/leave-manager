package fr.enssat.leave_manager.repo;

import fr.enssat.leave_manager.model.TypeOfLeave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeOfLeaveRepository extends JpaRepository<TypeOfLeave, Integer> {
}
