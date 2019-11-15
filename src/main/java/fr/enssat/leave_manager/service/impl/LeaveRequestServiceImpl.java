package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.LeaveRequestEntity;
import fr.enssat.leave_manager.repository.LeaveRequestRepository;
import fr.enssat.leave_manager.service.LeaveRequestService;
import fr.enssat.leave_manager.service.exception.LeaveRequestNotFound;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository repository;

    public LeaveRequestServiceImpl(LeaveRequestRepository repository) {
        this.repository = repository;
    }

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
    public void deleteLeaveRequest(LeaveRequestEntity lr) {
        this.repository.delete(lr);
    }
}
