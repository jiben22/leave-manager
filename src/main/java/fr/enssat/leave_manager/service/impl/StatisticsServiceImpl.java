package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.HREntity;
import fr.enssat.leave_manager.model.TypeOfLeaveEntity;
import fr.enssat.leave_manager.repository.*;
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
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private HRRepository hrRepository;

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
    public HashMap<String, List> getTreatedLeaveRequestByHR() {
        List<HREntity> hrEntities = hrRepository.findAll();
        HashMap<String, List> result = new HashMap<>();
        for (HREntity hr: hrEntities) {
            List<Integer> res = new ArrayList<>();
            for (LeaveStatus s : LeaveStatus.values())
                res.add(leaveRequestRepository.countAllByHrAndStatusAndCreationDateAfter(hr, s, LocalDateTime.now().minusMonths(1)));

            result.put(hr.getEmployee().getEmail(), res);
        }

        return result;
    }

    @Override
    public List<Object> getEffectiveByYear() {
        List<String> months = Arrays.asList("J", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D");
        List<Integer> result = new ArrayList<>();
        Integer effective = employeeRepository.countAll();
        int year = LocalDateTime.now().getYear();
        for (int i=1 ; i<=12 ; i++) {
            Integer r = leaveRequestRepository.countAllByStatusAndLastEditionDateBetweenAndGroupByEmployee(
                    LeaveStatus.ACCEPTED,
                    LocalDateTime.of(year, i, 1, 0, 0),
                    LocalDateTime.of(i+1<=12 ? year : year+1, i%12 +1, 1, 0, 0));
            result.add(effective - (r == null ? 0 : r));
        }

        return Arrays.asList(months, result);
    }

    @Override
    public HashMap<String, Integer> getMetrics() {
        HashMap<String, Integer> result = new HashMap<>();

        result.put("nb_department", departmentRepository.countAll());
        result.put("nb_team", teamRepository.countAll());
        result.put("nb_employee", employeeRepository.countAll());
        result.put("declined_leave_request", leaveRequestRepository.countAllByStatus(LeaveStatus.DECLINED));
        result.put("accepted_leave_request", leaveRequestRepository.countAllByStatus(LeaveStatus.ACCEPTED));
        result.put("cancelled_leave_request", leaveRequestRepository.countAllByStatus(LeaveStatus.CANCELLED));
        result.put("pending_leave_request", leaveRequestRepository.countAllByStatus(LeaveStatus.PENDING));

        return result;
    }
}
