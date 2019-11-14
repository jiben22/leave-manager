package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.model.TypeOfLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeOfLeaveRepository extends JpaRepository<TypeOfLeave, String> {
    List<TypeOfLeave> findByName(String name);
}
