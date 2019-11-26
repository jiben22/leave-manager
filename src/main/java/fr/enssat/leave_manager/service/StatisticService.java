package fr.enssat.leave_manager.service;

import java.util.List;
import java.util.Map;

public interface StatisticService {
    Map<String, Integer> getLeaveByStatus();
    Map<String, Integer> getLeaveByTypes();
    List<List> getAcceptedLeaveByYear();
    Map<String, List> getTreatedLeaveRequestByHR();
}
