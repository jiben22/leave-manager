package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.TypeOfLeave;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface TypeOfLeaveService {
    TypeOfLeave getTypeOfLeave(String id);
    List<TypeOfLeave> getTypeOfLeaveByName(String name);
    List<TypeOfLeave> getTypeOfLeaves();
    TypeOfLeave addTypeOfLeave(TypeOfLeave typeOfLeave);
    TypeOfLeave editTypeOfLeave(TypeOfLeave typeOfLeave);
    void deleteTypeOfLeave(String id);
    void deleteTypeOfLeave(TypeOfLeave typeOfLeave);
}
