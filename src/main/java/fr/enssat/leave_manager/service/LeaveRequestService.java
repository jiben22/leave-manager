package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.LeaveRequestEntity;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LeaveRequestService {
    boolean exists(String id);
    LeaveRequestEntity getLeaveRequest(String id);
    List<LeaveRequestEntity> getLeaveRequestByStatus(LeaveStatus status);
    List<LeaveRequestEntity> getLeaveRequests();
    LeaveRequestEntity addLeaveRequest(LeaveRequestEntity lr);
    LeaveRequestEntity editLeaveRequest(LeaveRequestEntity lr);
    LeaveRequestEntity acceptLeaveRequest(LeaveRequestEntity lr);
    LeaveRequestEntity declineLeaveRequest(LeaveRequestEntity lr);
    LeaveRequestEntity cancelLeaveRequest(LeaveRequestEntity lr);
    void deleteLeaveRequest(String lrid);
}
