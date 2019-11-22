package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.factory.LeaveRequestFactory;
import fr.enssat.leave_manager.model.LeaveRequestEntity;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LeaveRequestRepositoryTest {
    @Autowired
    private LeaveRequestRepository repository;

    @Test
    public void testGetLeaveRequest() {
        Optional<LeaveRequestEntity> opt_leave_request = repository.findById("LEAVEREQUEST-157314099170606-0001");

        assertTrue(opt_leave_request.isPresent());

        LeaveRequestEntity leave_request = opt_leave_request.get();
        assertEquals(leave_request.getLrid(), "LEAVEREQUEST-157314099170606-0001");
        assertEquals(leave_request.getReason(), "vacances");
        assertEquals(leave_request.getStatus(), LeaveStatus.PENDING);
        assertEquals(leave_request.getCreationDate(), LocalDateTime.of(2019, 11, 17, 18, 10));
        assertEquals(leave_request.getLastEditionDate(), LocalDateTime.of(2019, 11, 17, 18, 10));
        assertEquals(leave_request.getStartingDate(), LocalDateTime.of(2019, 11, 20, 0, 0));
        assertEquals(leave_request.getEndingDate(), LocalDateTime.of(2019, 11, 28, 23, 59));
        assertEquals(leave_request.getHrComment(), "");
    }

    @Test
    public void testGetLeaveRequestException() {
        Optional<LeaveRequestEntity> opt_leave_request = repository.findById("Unknown ID");

        assertFalse(opt_leave_request.isPresent());
    }

    @Test
    public void testGetAcceptedLeaveRequest() {
        List<LeaveRequestEntity> accepted_leave_request = repository.findAllByStatus(LeaveStatus.ACCEPTED);

        assertNotNull(accepted_leave_request);
        accepted_leave_request.forEach(leave_request -> assertEquals(leave_request.getStatus(), LeaveStatus.ACCEPTED));
    }

    @Test
    public void testGetLeaveRequests() {
        List<LeaveRequestEntity> leave_request_list = repository.findAll(Sort.by(Sort.Direction.ASC, "creationDate"));

        assertNotNull(leave_request_list);
        assertNotEquals(leave_request_list.size(), 0);
    }

    @Test
    public void testSaveLeaveRequest() {
        LeaveRequestEntity leave_request = LeaveRequestFactory.getLeaveRequest();
        LeaveRequestEntity added_leave_request = repository.saveAndFlush(leave_request);

        assertEquals(leave_request.getLrid(), added_leave_request.getLrid());
        assertEquals(leave_request.getReason(), added_leave_request.getReason());
        assertEquals(leave_request.getStatus(), added_leave_request.getStatus());
        assertEquals(leave_request.getCreationDate(), added_leave_request.getCreationDate());
        assertEquals(leave_request.getLastEditionDate(), added_leave_request.getLastEditionDate());
        assertEquals(leave_request.getStartingDate(), added_leave_request.getStartingDate());
        assertEquals(leave_request.getEndingDate(), added_leave_request.getEndingDate());
        assertEquals(leave_request.getHrComment(), added_leave_request.getHrComment());
    }

    @Test
    public void testDeleteLeaveRequestById() {
        repository.deleteById("LEAVEREQUEST-157314099170606-0001");

        assertFalse(repository.existsById("LEAVEREQUEST-157314099170606-0001"));
    }
}
