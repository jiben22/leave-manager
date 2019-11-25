package fr.enssat.leave_manager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public interface TimeTableService {
    boolean isAvailable(String eid, LocalDateTime start, LocalDateTime end);
}
