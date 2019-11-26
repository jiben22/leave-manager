package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.service.TimeTableService;
import fr.enssat.leave_manager.utils.TimetableSimulator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeTableServiceImpl implements TimeTableService {
    @Override
    public boolean isAvailable(String eid, LocalDateTime start_date, LocalDateTime end_date) {
        return TimetableSimulator.isAvailable(eid, start_date, end_date);
    }
}
