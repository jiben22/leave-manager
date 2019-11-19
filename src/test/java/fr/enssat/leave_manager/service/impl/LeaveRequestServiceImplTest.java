package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.LeaveRequestFactory;
import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.LeaveRequestEntity;
import fr.enssat.leave_manager.repository.LeaveRequestRepository;
import fr.enssat.leave_manager.service.EmployeeService;
import fr.enssat.leave_manager.service.exception.already_exists.LeaveRequestAlreadyExistsException;
import fr.enssat.leave_manager.service.exception.not_found.LeaveRequestNotFoundException;
import fr.enssat.leave_manager.service.exception.LeaveRequestRemainingLeaveException;
import fr.enssat.leave_manager.service.exception.LeaveRequestStatusException;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class LeaveRequestServiceImplTest {
    @Mock
    private LeaveRequestRepository repository;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private LeaveRequestServiceImpl leaveRequestService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetLeaveRequest() {
        Optional<LeaveRequestEntity> opt_leave_request_3 = LeaveRequestFactory.getOptLeaveRequest3();
        when(repository.findById("LEAVEREQUEST-157314099170606-0003"))
                .thenReturn(opt_leave_request_3);

        LeaveRequestEntity leaveRequest = leaveRequestService.getLeaveRequest("LEAVEREQUEST-157314099170606-0003");

        LeaveRequestEntity leave_request_3 = opt_leave_request_3.get();

        assertEquals(leaveRequest.getLrid(), leave_request_3.getLrid());
        assertEquals(leaveRequest.getReason(), leave_request_3.getReason());
        assertEquals(leaveRequest.getStatus(), leave_request_3.getStatus());
        assertEquals(leaveRequest.getCreationDate(), leave_request_3.getCreationDate());
        assertEquals(leaveRequest.getLastEditionDate(), leave_request_3.getLastEditionDate());
        assertEquals(leaveRequest.getStartingDate(), leave_request_3.getStartingDate());
        assertEquals(leaveRequest.getHrComment(), leave_request_3.getHrComment());
    }

    @Test(expected = LeaveRequestNotFoundException.class)
    public void testGetLeaveRequestException() {
        when(repository.findById("Unknown ID")).thenThrow(LeaveRequestNotFoundException.class);
        LeaveRequestEntity leave_request = leaveRequestService.getLeaveRequest("Unknown ID");
    }

    @Test
    public void testGetAcceptedLeaveRequest() {
        List<LeaveRequestEntity> list = new ArrayList<>();
        list.add(LeaveRequestFactory.getLeaveRequest3());
        when(repository.findAllByStatus(LeaveStatus.ACCEPTED))
                .thenReturn(list);

        List<LeaveRequestEntity> leave_request_list = leaveRequestService.getLeaveRequestByStatus(LeaveStatus.ACCEPTED);

        leave_request_list.forEach(e -> assertEquals(e.getStatus(), LeaveStatus.ACCEPTED));
    }

    @Test
    public void testGetLeaveRequests() {
        List<LeaveRequestEntity> list = new ArrayList<LeaveRequestEntity>();
        list.add(LeaveRequestFactory.getLeaveRequest());

        when(repository.findAll(Sort.by(Sort.Direction.ASC, "creationDate"))).thenReturn(list);

        List<LeaveRequestEntity> leave_requests = leaveRequestService.getLeaveRequests();

        assertNotNull(leave_requests);
        assertNotEquals(leave_requests.size(), 0);
    }

    @Test
    public void testAddLeaveRequest() {
        LeaveRequestEntity leave_request = LeaveRequestFactory.getLeaveRequest();
        EmployeeEntity emp = leave_request.getEmployee();
        double remaining = emp.getRemainingLeave();
        double duration = Duration.between(leave_request.getStartingDate(), leave_request.getEndingDate()).toDays();

        when(repository.saveAndFlush(leave_request)).thenReturn(leave_request);
        when(employeeService.editEmployee(emp)).thenReturn(emp);

        // Test
        LeaveRequestEntity added_leave_request = leaveRequestService.addLeaveRequest(leave_request);

        assertEquals(leave_request.getLrid(), added_leave_request.getLrid());
        assertEquals(leave_request.getReason(), added_leave_request.getReason());
        assertEquals(leave_request.getStatus(), added_leave_request.getStatus());
        assertEquals(leave_request.getCreationDate(), added_leave_request.getCreationDate());
        assertEquals(leave_request.getLastEditionDate(), added_leave_request.getLastEditionDate());
        assertEquals(leave_request.getStartingDate(), added_leave_request.getStartingDate());
        assertEquals(leave_request.getHrComment(), added_leave_request.getHrComment());

        assertTrue(added_leave_request.getEmployee().getRemainingLeave() == remaining - duration);
    }

    @Test(expected = LeaveRequestRemainingLeaveException.class)
    public void testAddLeaveRequestException() {
        LeaveRequestEntity leave_request = LeaveRequestFactory.getLongLeaveRequest();

        LeaveRequestEntity added_leave_request = leaveRequestService.addLeaveRequest(leave_request);
    }

    @Test(expected = LeaveRequestAlreadyExistsException.class)
    public void testAddLeaveRequestExceptionAlreadyExist() {
        LeaveRequestEntity leave_request = LeaveRequestFactory.getLeaveRequest1();

        when(repository.existsById(leave_request.getLrid()))
                .thenReturn(true);

        LeaveRequestEntity added_leave_request = leaveRequestService.addLeaveRequest(leave_request);
    }

    @Test
    public void testEditLeaveRequest() {
        LeaveRequestEntity old_leave_request = LeaveRequestFactory.getLeaveRequest1();
        LeaveRequestEntity leave_request = LeaveRequestFactory.getLeaveRequest1();
        EmployeeEntity emp = leave_request.getEmployee();
        double remaining = emp.getRemainingLeave();

        long old_day = Duration.between(old_leave_request.getStartingDate(), old_leave_request.getEndingDate()).toDays();
        long day = Duration.between(leave_request.getStartingDate(), leave_request.getEndingDate()).toDays();

        when(repository.saveAndFlush(leave_request)).thenReturn(leave_request);
        when(repository.existsById(leave_request.getLrid())).thenReturn(true);
        when(employeeService.editEmployee(emp)).thenReturn(emp);
        when(repository.findById(leave_request.getLrid()))
                .thenReturn(Optional.of(old_leave_request));

        // Test
        LeaveRequestEntity edited_leave_request = leaveRequestService.editLeaveRequest(leave_request);

        assertEquals(leave_request.getLrid(), edited_leave_request.getLrid());
        assertEquals(leave_request.getReason(), edited_leave_request.getReason());
        assertEquals(leave_request.getStatus(), edited_leave_request.getStatus());
        assertEquals(leave_request.getCreationDate(), edited_leave_request.getCreationDate());
        assertTrue(Duration.between(
                        old_leave_request.getLastEditionDate(),
                        edited_leave_request.getLastEditionDate()).toNanos()
                        >= 0.0);
        assertEquals(leave_request.getStartingDate(), edited_leave_request.getStartingDate());
        assertEquals(leave_request.getHrComment(), edited_leave_request.getHrComment());

        assertTrue(leave_request.getEmployee().getRemainingLeave()
                == remaining + old_day - day);
    }

    @Test(expected = LeaveRequestRemainingLeaveException.class)
    public void testEditLeaveRequestException() {
        LeaveRequestEntity old_leave_request = LeaveRequestFactory.getLeaveRequest1();
        LeaveRequestEntity leave_request = LeaveRequestFactory.getLeaveRequest1();
        leave_request.setEndingDate(LocalDateTime.of(2020, 11, 23, 0, 0, 0));

        when(repository.existsById(leave_request.getLrid())).thenReturn(true);
        when(repository.findById(leave_request.getLrid()))
                .thenReturn(Optional.of(old_leave_request));

        LeaveRequestEntity added_leave_request = leaveRequestService.editLeaveRequest(leave_request);
    }

    @Test(expected = LeaveRequestStatusException.class)
    public void testEditLeaveRequestExceptionStatus() {
        LeaveRequestEntity leave_request = LeaveRequestFactory.getLeaveRequest3();
        leave_request.setEndingDate(LocalDateTime.of(2019, 11, 23, 0, 0, 0));
        when(repository.existsById(leave_request.getLrid())).thenReturn(true);

        LeaveRequestEntity added_leave_request = leaveRequestService.editLeaveRequest(leave_request);
    }
}
