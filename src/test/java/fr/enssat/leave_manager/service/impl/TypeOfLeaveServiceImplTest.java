package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.factory.TypeOfLeaveFactory;
import fr.enssat.leave_manager.model.TypeOfLeaveEntity;
import fr.enssat.leave_manager.repository.TypeOfLeaveRepository;
import fr.enssat.leave_manager.service.exception.not_found.TypeOfLeaveNotFoundException;
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
public class TypeOfLeaveServiceImplTest {

    @Mock
    private TypeOfLeaveRepository repository;

    @InjectMocks
    private TypeOfLeaveServiceImpl typeOfLeaveService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetTypeOfLeave() {

        Optional<TypeOfLeaveEntity> typeOfLeave1 = TypeOfLeaveFactory.getTypeOfLeave1();

        when(repository.findById(typeOfLeave1.get().getId()))
                .thenReturn(typeOfLeave1);

        TypeOfLeaveEntity typeOfLeave = typeOfLeaveService.getTypeOfLeave(typeOfLeave1.get().getId());

        assertThat(typeOfLeave).isEqualToComparingFieldByField(typeOfLeave1.get());
    }

    @Test(expected = TypeOfLeaveNotFoundException.class)
    public void testGetTypeOfLeaveException() {

        when(repository.findById("Unknown ID")).thenThrow(TypeOfLeaveNotFoundException.class);
        typeOfLeaveService.getTypeOfLeave("Unknown ID");
    }

    @Test
    public void testGetTypeOfLeaveByNameAndIsArchivedFalse() {

        List<TypeOfLeaveEntity> typeOfLeaveList1 = new ArrayList<>();
        typeOfLeaveList1.add(TypeOfLeaveFactory.getTypeOfLeave1().get());

        when(repository.findByNameAndIsArchivedFalse("CA"))
                .thenReturn(typeOfLeaveList1);

        List<TypeOfLeaveEntity> typeOfLeaveList = typeOfLeaveService.getTypeOfLeaveByNameAndIsArchivedFalse("CA");

        assertEquals(typeOfLeaveList.size(), typeOfLeaveList1.size());
        assertThat(typeOfLeaveList.get(0)).isEqualToComparingFieldByField(typeOfLeaveList1.get(0));
    }

    @Test(expected = TypeOfLeaveNotFoundException.class)
    public void testGetTypeOfLeaveByNameAndIsArchivedFalseException() {

        when(repository.findByNameAndIsArchivedFalse("Unknown"))
                .thenThrow(TypeOfLeaveNotFoundException.class);

        typeOfLeaveService.getTypeOfLeaveByNameAndIsArchivedFalse("Unknown");
    }

    @Test
    public void testGetTypeOfLeavesByIsArchivedFalse() {

        List<TypeOfLeaveEntity> typeOfLeaveList1 = new ArrayList<>();
        typeOfLeaveList1.add(TypeOfLeaveFactory.getTypeOfLeave1().get());
        typeOfLeaveList1.add(TypeOfLeaveFactory.getOptTypeOfLeave2().get());

        when(repository.findAllByIsArchivedFalse())
                .thenReturn(typeOfLeaveList1);

        List<TypeOfLeaveEntity> typeOfLeaveList = typeOfLeaveService.getTypeOfLeaves();

        assertEquals(typeOfLeaveList.size(), typeOfLeaveList1.size());
        assertThat(typeOfLeaveList.get(0)).isEqualToComparingFieldByField(typeOfLeaveList1.get(0));
        assertThat(typeOfLeaveList.get(1)).isEqualToComparingFieldByField(typeOfLeaveList1.get(1));
    }

    @Test(expected = TypeOfLeaveNotFoundException.class)
    public void testGetTypeOfLeavesByIsArchivedFalseException() {
        when(repository.findAllByIsArchivedFalse())
                .thenThrow(TypeOfLeaveNotFoundException.class);

        typeOfLeaveService.getTypeOfLeaves();
    }

    @Test
    public void testGetArchivedTypeOfLeaves() {

        List<TypeOfLeaveEntity> typeOfLeaveList1 = new ArrayList<>();
        typeOfLeaveList1.add(TypeOfLeaveFactory.getTypeOfLeave4().get());

        when(repository.findAllByIsArchivedTrue())
                .thenReturn(typeOfLeaveList1);

        List<TypeOfLeaveEntity> typeOfLeaveList = typeOfLeaveService.getArchivedTypeOfLeaves();

        assertEquals(typeOfLeaveList.size(), typeOfLeaveList1.size());
        assertThat(typeOfLeaveList.get(0)).isEqualToComparingFieldByField(typeOfLeaveList1.get(0));
    }

    @Test(expected = TypeOfLeaveNotFoundException.class)
    public void testGetArchivedTypeOfLeavesException() {

        when(repository.findAllByIsArchivedTrue())
                .thenThrow(TypeOfLeaveNotFoundException.class);

        typeOfLeaveService.getArchivedTypeOfLeaves();
    }

    @Test
    public void testAddTypeOfLeave() {

        TypeOfLeaveEntity typeOfLeave = TypeOfLeaveFactory.getTypeOfLeave();
        when(repository.saveAndFlush(typeOfLeave))
                .thenReturn(typeOfLeave);

        TypeOfLeaveEntity addedTypeOfLeave = typeOfLeaveService.addTypeOfLeave(typeOfLeave);

        assertThat(addedTypeOfLeave).isEqualToComparingFieldByField(typeOfLeave);
    }

    @Test
    public void testEditTypeOfLeave() {

        TypeOfLeaveEntity typeOfLeave = TypeOfLeaveFactory.getTypeOfLeave2();
        typeOfLeave.setName("CA2");

        when(repository.saveAndFlush(typeOfLeave))
                .thenReturn(typeOfLeave);
        when(repository.existsById(typeOfLeave.getId()))
                .thenReturn(true);

        TypeOfLeaveEntity editedTypeOfLeave = typeOfLeaveService.editTypeOfLeave(typeOfLeave);

        assertThat(editedTypeOfLeave).isEqualToComparingFieldByField(typeOfLeave);
    }

//    @Test
//    public void testDeleteTypeOfLeave() {
//
//        when(repository.findById("TYPEOFLEAVE-157314099170606-0001"))
//                .thenReturn(TypeOfLeaveFactory.getTypeOfLeave1());
//
//        TypeOfLeaveEntity archivedTypeOfLeave = TypeOfLeaveFactory.getTypeOfLeave1().get();
//        archivedTypeOfLeave.setIsArchived(true);
//
//        when(repository.saveAndFlush(archivedTypeOfLeave))
//                .thenReturn(archivedTypeOfLeave);
//
//        TypeOfLeaveEntity deletedTypeOfLeave =
//                typeOfLeaveService.deleteTypeOfLeave("TYPEOFLEAVE-157314099170606-0001");
//
//        assertTrue(deletedTypeOfLeave.getIsArchived());
//    }
}
