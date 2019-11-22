package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.model.LeaveRequestEntity;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequestEntity, String> {
    List<LeaveRequestEntity> findAllByStatus(LeaveStatus status); // method automatically created
}
