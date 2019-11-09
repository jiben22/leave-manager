package fr.enssat.leave_manager.repo;

import fr.enssat.leave_manager.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {
}
