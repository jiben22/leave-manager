package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.TypeOfLeaveEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface TypeOfLeaveService {
    boolean exists(String id);
    TypeOfLeaveEntity getTypeOfLeave(String id);
    List<TypeOfLeaveEntity> getTypeOfLeaveByName(String name);
    List<TypeOfLeaveEntity> getTypeOfLeaves();
    TypeOfLeaveEntity addTypeOfLeave(TypeOfLeaveEntity typeOfLeave);
    TypeOfLeaveEntity editTypeOfLeave(TypeOfLeaveEntity typeOfLeave);
    void deleteTypeOfLeave(String id);
}
