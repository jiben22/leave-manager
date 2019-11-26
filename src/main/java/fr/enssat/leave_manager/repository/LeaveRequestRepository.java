package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.model.HREntity;
import fr.enssat.leave_manager.model.LeaveRequestEntity;
import fr.enssat.leave_manager.model.TypeOfLeaveEntity;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequestEntity, String> {
    List<LeaveRequestEntity> findAllByStatus(LeaveStatus status); // method automatically created

    Integer countAllByStatusAndCreationDateAfter(LeaveStatus status, LocalDateTime creation);

    Integer countAllByTypeOfLeaveAndCreationDateAfter(TypeOfLeaveEntity type, LocalDateTime creation);

    Integer countAllByLastEditionDateBetweenAndStatus(LocalDateTime start, LocalDateTime end, LeaveStatus status);

    Integer countByHrAndStatusAndCreationDateAfter(HREntity hr, LeaveStatus status, LocalDateTime creation);
}
