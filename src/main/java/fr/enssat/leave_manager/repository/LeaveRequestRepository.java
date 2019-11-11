package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, String> {
    List<LeaveRequest> findAllByStatus(String status); // method automatically created
}
