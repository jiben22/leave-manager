package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.TypeOfLeaveEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TypeOfLeaveService {
    boolean exists(String id);
    TypeOfLeaveEntity getTypeOfLeave(String id);
    List<TypeOfLeaveEntity> getTypeOfLeaveByNameAndIsArchivedFalse(String name);
    List<TypeOfLeaveEntity> getTypeOfLeaves();
    List<TypeOfLeaveEntity> getAllTypeofLeaves();
    TypeOfLeaveEntity addTypeOfLeave(TypeOfLeaveEntity typeOfLeave);
    TypeOfLeaveEntity editTypeOfLeave(TypeOfLeaveEntity typeOfLeave);
    TypeOfLeaveEntity deleteTypeOfLeave(String id);
}
