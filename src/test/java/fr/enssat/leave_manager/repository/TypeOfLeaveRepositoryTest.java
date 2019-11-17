package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.factory.TypeOfLeaveFactory;
import fr.enssat.leave_manager.model.TypeOfLeaveEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TypeOfLeaveRepositoryTest {
    @Autowired
    private TypeOfLeaveRepository repository;

    @Test
    public void testGetTypeOfLeave() {
        Optional<TypeOfLeaveEntity> opt_type_of_leave = repository.findById("TYPEOFLEAVE-157314099170606-0001");

        assertTrue(opt_type_of_leave.isPresent());

        TypeOfLeaveEntity type_of_leave = opt_type_of_leave.get();
        assertEquals(type_of_leave.getId(), "TYPEOFLEAVE-157314099170606-0001");
        assertEquals(type_of_leave.getName(), "CA");
        assertEquals(type_of_leave.getDescription(), "Congés Annuels");
    }

    @Test
    public void testGetTypeOfLeaveException() {
        Optional<TypeOfLeaveEntity> opt_type_of_leave = repository.findById("UNKNOWN ID");

        assertFalse(opt_type_of_leave.isPresent());
    }

    @Test
    public void testGetTypeOfLeaveByName() {
        List<TypeOfLeaveEntity> type_of_leave_list = repository.findByNameAndIsArchivedFalse("CA");

        assertNotNull(type_of_leave_list);
        assertNotEquals(type_of_leave_list.size(), 0);

        type_of_leave_list.forEach(
                type -> assertFalse(type.getIsArchived())
        );

        TypeOfLeaveEntity type_of_leave = type_of_leave_list.get(0);

        assertEquals(type_of_leave.getId(), "TYPEOFLEAVE-157314099170606-0001");
        assertEquals(type_of_leave.getName(), "CA");
        assertEquals(type_of_leave.getDescription(), "Congés Annuels");
    }

    @Test
    public void testGetTypeOfLeaves() {
        List<TypeOfLeaveEntity> type_of_leave_list = repository.findAllByIsArchivedFalse();

        assertNotNull(type_of_leave_list);
        assertNotEquals(type_of_leave_list.size(), 0);

        type_of_leave_list.forEach(
                type -> assertFalse(type.getIsArchived())
        );
    }

    @Test
    public void testGetArchivedTypeOfLeaves() {
        List<TypeOfLeaveEntity> type_of_leave_list = repository.findAllByIsArchivedTrue();

        assertNotNull(type_of_leave_list);
        assertNotEquals(type_of_leave_list.size(), 0);

        type_of_leave_list.forEach(
                type -> assertTrue(type.getIsArchived())
        );
    }

    @Test
    public void testGetTypeOfLeavesWithArchived() {
        List<TypeOfLeaveEntity> type_of_leave_list = repository.findAll();

        assertNotNull(type_of_leave_list);
        assertNotEquals(type_of_leave_list.size(), 0);
    }

    @Test
    public void testSaveTypeOfLeave() {
        TypeOfLeaveEntity type_of_leave = TypeOfLeaveFactory.getTypeOfLeave();
        TypeOfLeaveEntity added_type_of_leave = repository.save(type_of_leave);

        assertEquals(type_of_leave.getId(), added_type_of_leave.getId());
        assertEquals(type_of_leave.getName(), added_type_of_leave.getName());
        assertEquals(type_of_leave.getDescription(), added_type_of_leave.getDescription());
    }
}
