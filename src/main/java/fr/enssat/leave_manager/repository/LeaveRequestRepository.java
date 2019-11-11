package fr.enssat.leave_manager.repo;

import fr.enssat.leave_manager.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, String> {
    List<LeaveRequest> findAllByStatus(String status); // method automatically created
}
