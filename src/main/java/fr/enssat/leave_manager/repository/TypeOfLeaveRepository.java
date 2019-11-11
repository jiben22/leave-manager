package fr.enssat.leave_manager.repo;

import fr.enssat.leave_manager.model.TypeOfLeave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeOfLeaveRepository extends JpaRepository<TypeOfLeave, String> {
    Optional<TypeOfLeave> findByName(String name); // method automatically created
}
