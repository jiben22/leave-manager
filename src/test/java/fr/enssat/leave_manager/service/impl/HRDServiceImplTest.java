package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.HRDFactory;
import fr.enssat.leave_manager.model.HRDEntity;
import fr.enssat.leave_manager.repository.HRDRepository;
import fr.enssat.leave_manager.service.exception.not_found.HRDNotFoundException;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class HRDServiceImplTest {
    @Mock
    private HRDRepository repository;

    @InjectMocks
    private HRDServiceImpl hrdService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetHRD() {
        HRDEntity hrd5 = HRDFactory.getHRD5();

        when(repository.findById(hrd5.getEid()))
                .thenReturn(Optional.of(hrd5));

        HRDEntity hrd = hrdService.getHRD(hrd5.getEid());

        assertThat(hrd).isEqualToComparingFieldByField(hrd5);
    }

    @Test(expected = HRDNotFoundException.class)
    public void testGetHRDException() {
        when(repository.findById("UNKNOWN ID"))
                .thenThrow(HRDNotFoundException.class);

        hrdService.getHRD("UNKNOWN ID");
    }

    @Test
    public void testGetHRDs() {
        List<HRDEntity> list = new ArrayList<>();
        list.add(HRDFactory.getHRD5());

        when(repository.findAll())
                .thenReturn(list);

        List<HRDEntity> HRDList = hrdService.getHRDs();

        assertNotNull(HRDList);
        assertNotEquals(HRDList.size(), 0);
    }

    @Test
    public void testAddHRD() {
        HRDEntity hrd = HRDFactory.getHRD();

        when(repository.saveAndFlush(hrd))
                .thenReturn(hrd);

        HRDEntity added_hrd = hrdService.addHRD(hrd);

        assertThat(hrd).isEqualToComparingFieldByField(added_hrd);
    }

    @Test
    public void testDeleteHRD() {
        hrdService.deleteHRD("EMPLOYEE-157314099170606-0002");

        assertFalse(hrdService.exists("EMPLOYEE-157314099170606-0002"));
    }
}
