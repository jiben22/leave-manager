package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.LeaveRequestEntity;
import fr.enssat.leave_manager.repository.LeaveRequestRepository;
import fr.enssat.leave_manager.service.LeaveRequestService;
import fr.enssat.leave_manager.service.exception.LeaveRequestNotFound;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {
    @Autowired
    private LeaveRequestRepository repository;

    @Override
    public boolean exists(String id) {
        return repository.existsById(id);
    }

    @Override
    public LeaveRequestEntity getLeaveRequest(String id) {
        return this.repository.findById(id).orElseThrow(()->new LeaveRequestNotFound(id));
    }

    @Override
    public List<LeaveRequestEntity> getLeaveRequestByStatus(LeaveStatus status) {
        return this.repository.findAllByStatus(status);
    }

    @Override
    public LeaveRequestEntity addLeaveRequest(LeaveRequestEntity lr) {
        return this.repository.save(lr);
    }

    @Override
    public LeaveRequestEntity editLeaveRequest(LeaveRequestEntity lr) {
        return this.repository.save(lr);
    }

    @Override
    public LeaveRequestEntity acceptLeaveRequest(LeaveRequestEntity lr) {
        lr.setStatus(LeaveStatus.ACCEPTED);
        return this.repository.save(lr);
    }

    @Override
    public LeaveRequestEntity declineLeaveRequest(LeaveRequestEntity lr) {
        lr.setStatus(LeaveStatus.DECLINED);
        return this.repository.save(lr);
    }

    @Override
    public LeaveRequestEntity cancelLeaveRequest(LeaveRequestEntity lr) {
        lr.setStatus(LeaveStatus.CANCELLED);
        return this.repository.save(lr);
    }

    @Override
    public void deleteLeaveRequest(String lrid) {
        this.repository.deleteById(lrid);
    }
}
