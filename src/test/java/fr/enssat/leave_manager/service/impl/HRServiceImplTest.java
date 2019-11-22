package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.HRFactory;
import fr.enssat.leave_manager.model.HREntity;
import fr.enssat.leave_manager.repository.HRRepository;
import fr.enssat.leave_manager.service.exception.not_found.HRNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class HRServiceImplTest {
    @Mock
    private HRRepository repository;

    @InjectMocks
    private HRServiceImpl hrService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetHR() {
        HREntity hr10 = HRFactory.getHR10();

        when(repository.findById(hr10.getEid()))
                .thenReturn(Optional.of(hr10));

        HREntity hr = hrService.getHR(hr10.getEid());

        assertThat(hr).isEqualToComparingFieldByField(hr10);
    }

    @Test(expected = HRNotFoundException.class)
    public void testGetHRDException() {
        when(repository.findById("UNKNOWN ID"))
                .thenThrow(HRNotFoundException.class);

        hrService.getHR("UNKNOWN ID");
    }

    @Test
    public void testGetHRs() {
        List<HREntity> list = new ArrayList<>();
        list.add(HRFactory.getHR10());

        when(repository.findAll())
                .thenReturn(list);

        List<HREntity> hrs = hrService.getHRs();

        assertNotNull(hrs);
        assertNotEquals(hrs.size(), 0);
    }

    @Test
    public void testAddHR() {
        HREntity hr = HRFactory.getHR();

        when(repository.saveAndFlush(hr))
                .thenReturn(hr);

        HREntity added_hr = hrService.addHR(hr);

        assertThat(hr).isEqualToComparingFieldByField(added_hr);
    }

    @Test
    public void testDeleteHR() {
        hrService.deleteHR("EMPLOYEE-157314099170606-0010");

        assertFalse(hrService.exists("EMPLOYEE-157314099170606-0010"));
    }
}
