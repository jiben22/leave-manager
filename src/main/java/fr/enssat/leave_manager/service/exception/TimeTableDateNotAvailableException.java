package fr.enssat.leave_manager.service.exception;

import fr.enssat.leave_manager.model.EmployeeEntity;

import java.time.LocalDateTime;

public class TimeTableDateNotAvailableException extends RuntimeException {
    public TimeTableDateNotAvailableException(EmployeeEntity emp, LocalDateTime start, LocalDateTime end) {
        super("Employee '"+emp.getEid()+"' is not avaible between '"+start.toString()+"' and '"+end.toString()+"' !");
    }
}
