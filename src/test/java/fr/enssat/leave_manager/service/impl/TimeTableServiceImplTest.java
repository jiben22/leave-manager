package fr.enssat.leave_manager.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
public class TimeTableServiceImplTest {

    @InjectMocks
    private TimeTableServiceImpl timeTableService;

    @Test
    public void testIsAvailable() {
        // reunion le 11
        boolean isAvailable = timeTableService.isAvailable(
                "EMPLOYEE-157314099170606-0001",
                LocalDateTime.of(2020, 1, 8, 0, 0, 0),
                LocalDateTime.of(2020, 1, 10, 23, 59, 0));

        assertTrue(isAvailable);
    }

    @Test
    public void testIsNotAvailable() {
        // reunion le 11 et 12
        boolean isAvailable = timeTableService.isAvailable(
                "EMPLOYEE-157314099170606-0001",
                LocalDateTime.of(2020, 1, 11, 0, 0, 0),
                LocalDateTime.of(2020, 1, 12, 23, 59, 0));

        assertFalse(isAvailable);
    }
}
