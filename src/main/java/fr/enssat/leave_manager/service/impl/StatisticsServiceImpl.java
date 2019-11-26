package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.repository.LeaveRequestRepository;
import fr.enssat.leave_manager.service.StatisticService;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticService {
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Override
    public Map<String, Integer> getLeaveByStatus() {
        HashMap<String, Integer> result = new HashMap<>();
        for (LeaveStatus s : LeaveStatus.values())
            result.put(s.toString(), leaveRequestRepository.countByStatusDuringLastMonth(s));

        return result;
    }
}
