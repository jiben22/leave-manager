package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.LeaveRequestFactory;
import fr.enssat.leave_manager.model.LeaveRequestEntity;
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

        LeaveRequestEntity leaveRequest = LeaveRequestFactory.getLeaveRequest();
    }
}
