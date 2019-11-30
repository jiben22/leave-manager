package fr.enssat.leave_manager.service;

import java.time.LocalDateTime;

public interface TimeTableService {
    boolean isAvailable(String eid, LocalDateTime start, LocalDateTime end);
}
