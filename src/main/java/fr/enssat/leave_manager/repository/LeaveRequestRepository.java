package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.model.LeaveRequestEntity;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequestEntity, String> {
    List<LeaveRequestEntity> findAllByStatus(LeaveStatus status); // method automatically created

    //l.creationDate >= (CURRENT_TIMESTAMP - MONTH(1)) AND
    @Query("SELECT count(*) FROM LeaveRequestEntity l WHERE l.status = :status")
    Integer countByStatusDuringLastMonth(@Param("status") LeaveStatus status);
}
