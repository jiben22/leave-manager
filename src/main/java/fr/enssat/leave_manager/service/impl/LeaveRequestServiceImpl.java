package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.LeaveRequestEntity;
import fr.enssat.leave_manager.repository.LeaveRequestRepository;
import fr.enssat.leave_manager.service.EmployeeService;
import fr.enssat.leave_manager.service.LeaveRequestService;
import fr.enssat.leave_manager.service.exception.*;
import fr.enssat.leave_manager.service.exception.already_exist.AlreadyExistException;
import fr.enssat.leave_manager.service.exception.already_exist.LeaveRequestAlreadyExistException;
import fr.enssat.leave_manager.service.exception.not_found.LeaveRequestNotFoundException;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {
    @Autowired
    private LeaveRequestRepository repository;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public boolean exists(String id) {
        return repository.existsById(id);
    }

    @Override
    public LeaveRequestEntity getLeaveRequest(String id) {
        return this.repository.findById(id).orElseThrow(()->new LeaveRequestNotFoundException(id));
    }

    @Override
    public List<LeaveRequestEntity> getLeaveRequestByStatus(LeaveStatus status) {
        return this.repository.findAllByStatus(status);
    }

    @Override
    public List<LeaveRequestEntity> getLeaveRequests() {
        return this.repository.findAll(Sort.by(Sort.Direction.ASC, "creationDate"));
    }

    @Override
    public LeaveRequestEntity addLeaveRequest(LeaveRequestEntity lr) {
        if (this.repository.existsById(lr.getLrid()))
            throw new LeaveRequestAlreadyExistException(lr);

        EmployeeEntity emp = lr.getEmployee();
        long day = Duration.between(lr.getStartingDate(), lr.getEndingDate()).toDays();
        if (emp.getRemainingLeave() - day < 0.0)
            throw new LeaveRequestRemainingLeaveException(emp.getRemainingLeave(), day);

        // remove leave from employee.getRemaining_leave(), day);
        emp.setRemainingLeave(emp.getRemainingLeave() - day);
        lr.setEmployee(employeeService.editEmployee(emp));

        return this.repository.saveAndFlush(lr);
    }

    @Override
    public LeaveRequestEntity editLeaveRequest(LeaveRequestEntity lr) {
        if (lr.getStatus() != LeaveStatus.PENDING)
            throw new LeaveRequestStatusException(lr.getStatus(), LeaveStatus.PENDING);

        LeaveRequestEntity last_request = this.getLeaveRequest(lr.getLrid());
        EmployeeEntity emp = lr.getEmployee();

        long last_day = Duration.between(last_request.getStartingDate(), last_request.getEndingDate()).toDays();
        long day = Duration.between(lr.getStartingDate(), lr.getEndingDate()).toDays();
        if (emp.getRemainingLeave() + last_day - day < 0.0)
            throw new LeaveRequestRemainingLeaveException(emp.getRemainingLeave()+last_day, day);

        // edit leave from employee.getRemaining_leave(), day);
        emp.setRemainingLeave(emp.getRemainingLeave() + last_day - day);
        lr.setEmployee(employeeService.editEmployee(emp));

        lr.setLastEditionDate(LocalDateTime.now()); // update last edition date

        return this.repository.saveAndFlush(lr);
    }

    @Override
    public LeaveRequestEntity acceptLeaveRequest(LeaveRequestEntity lr) {
        if (lr.getStatus() != LeaveStatus.PENDING)
            throw new LeaveRequestStatusException(lr.getStatus(), LeaveStatus.ACCEPTED);
        lr.setStatus(LeaveStatus.ACCEPTED);

        lr.setLastEditionDate(LocalDateTime.now()); // update last edition date

        return this.repository.saveAndFlush(lr);
    }

    @Override
    public LeaveRequestEntity declineLeaveRequest(LeaveRequestEntity lr) {
        if (lr.getStatus() != LeaveStatus.PENDING)
            throw new LeaveRequestStatusException(lr.getStatus(), LeaveStatus.DECLINED);

        if (lr.getHrComment() == null)
            throw new LeaveRequestCommentException();

        lr.setStatus(LeaveStatus.DECLINED);

        // edit leave from employee.getRemaining_leave(), day);
        EmployeeEntity emp = lr.getEmployee();
        long day = Duration.between(lr.getStartingDate(), lr.getEndingDate()).toDays();
        emp.setRemainingLeave(emp.getRemainingLeave() + day);
        lr.setEmployee(employeeService.editEmployee(emp));

        lr.setLastEditionDate(LocalDateTime.now()); // update last edition date

        return this.repository.saveAndFlush(lr);
    }

    @Override
    public LeaveRequestEntity cancelLeaveRequest(LeaveRequestEntity lr) {
        if (lr.getStatus() != LeaveStatus.DECLINED)
            throw new LeaveRequestStatusException(lr.getStatus(), LeaveStatus.DECLINED);

        lr.setStatus(LeaveStatus.CANCELLED);

        // edit leave from employee.getRemaining_leave(), day);
        EmployeeEntity emp = lr.getEmployee();
        long day = Duration.between(lr.getStartingDate(), lr.getEndingDate()).toDays();
        emp.setRemainingLeave(emp.getRemainingLeave() + day);
        lr.setEmployee(employeeService.editEmployee(emp));

        lr.setLastEditionDate(LocalDateTime.now()); // update last edition date

        return this.repository.saveAndFlush(lr);
    }

    @Override
    public void deleteLeaveRequest(String lrid) {
        this.repository.deleteById(lrid);
    }
}
