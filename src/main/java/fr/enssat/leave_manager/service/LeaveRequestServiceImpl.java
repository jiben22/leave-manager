package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveRequestServiceImpl implements TypeOfLeaveService {

    private final LeaveRequestRepository leaveRequestRepository;

    @Autowired
    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
    }
}
