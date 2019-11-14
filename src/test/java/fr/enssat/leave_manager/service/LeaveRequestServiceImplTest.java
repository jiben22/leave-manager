package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.factory.LeaveRequestFactory;
import fr.enssat.leave_manager.model.LeaveRequest;
import fr.enssat.leave_manager.service.impl.LeaveRequestServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class LeaveRequestServiceImplTest {

    @Mock
    private LeaveRequestServiceImpl leaveRequestService;

    @Test
    public void testGetLeaveRequest() {

        LeaveRequest leaveRequest = LeaveRequestFactory.getLeaveRequest();
    }
}
