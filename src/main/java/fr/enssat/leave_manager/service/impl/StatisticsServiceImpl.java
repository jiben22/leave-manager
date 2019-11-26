package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.HREntity;
import fr.enssat.leave_manager.model.TypeOfLeaveEntity;
import fr.enssat.leave_manager.repository.HRRepository;
import fr.enssat.leave_manager.repository.LeaveRequestRepository;
import fr.enssat.leave_manager.repository.TypeOfLeaveRepository;
import fr.enssat.leave_manager.service.StatisticService;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class StatisticsServiceImpl implements StatisticService {
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private TypeOfLeaveRepository typeOfLeaveRepository;

    @Autowired
    private HRRepository hrRepository;

    @Override
    public Map<String, Integer> getLeaveByStatus() {
        HashMap<String, Integer> result = new HashMap<>();
        for (LeaveStatus s : LeaveStatus.values())
            result.put(s.toString(), leaveRequestRepository.countAllByStatusAndCreationDateAfter(s, LocalDateTime.now().minusMonths(1)));

        return result;
    }

    @Override
    public Map<String, Integer> getLeaveByTypes() {
        List<TypeOfLeaveEntity> types = typeOfLeaveRepository.findAllByIsArchivedFalse();
        HashMap<String, Integer> result = new HashMap<>();
        for (TypeOfLeaveEntity t : types)
            result.put(t.getName(), leaveRequestRepository.countAllByTypeOfLeaveAndCreationDateAfter(t, LocalDateTime.now().minusMonths(1)));

        return result;
    }

    @Override
    public List<List> getAcceptedLeaveByYear() {
        List<String> months = Arrays.asList("J", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D");
        List<Integer> result = new ArrayList<>();
        int year = LocalDateTime.now().getYear();
        for (int i=1 ; i<=12 ; i++) {
            result.add(leaveRequestRepository.countAllByLastEditionDateBetweenAndStatus(
                    LocalDateTime.of(year, i, 1, 0, 0),
                    LocalDateTime.of(i+1<=12 ? year : year+1, i%12 +1, 1, 0, 0),
                    LeaveStatus.ACCEPTED));
        }

        return Arrays.asList(months, result);
    }

    @Override
    public Map<String, List> getTreatedLeaveRequestByHR() {
        List<HREntity> hrEntities = hrRepository.findAll();
        HashMap<String, List> result = new HashMap<>();
        for (HREntity hr: hrEntities) {
            List<Integer> res = new ArrayList<>();
            for (LeaveStatus s : LeaveStatus.values())
                res.add(leaveRequestRepository.countByHrAndStatusAndCreationDateAfter(hr, s, LocalDateTime.now().minusMonths(1)));
            result.put(hr.getEmployee().getEmail(), res);
        }

        return result;
    }
}
