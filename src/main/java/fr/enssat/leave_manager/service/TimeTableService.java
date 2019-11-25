package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.EmployeeEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public interface TimeTableService {
    boolean isAvailable(EmployeeEntity emp, LocalDateTime start, LocalDateTime end);
}
