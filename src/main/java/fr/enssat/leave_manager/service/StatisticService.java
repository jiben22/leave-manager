package fr.enssat.leave_manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface StatisticService {
    Map<String, Integer> getLeaveByTypes();
    List<List> getAcceptedLeaveByYear();
    HashMap<String, List> getTreatedLeaveRequestByHR();
    List<Object> getEffectiveByYear();
    HashMap<String, Integer> getMetrics();
}
