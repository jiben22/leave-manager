package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.utils.enums.LeaveStatus;

import java.util.Map;

public interface StatisticService {
    Map<String, Integer> getLeaveByStatus();
}
