package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.TypeOfLeaveEntity;
import fr.enssat.leave_manager.repository.TypeOfLeaveRepository;
import fr.enssat.leave_manager.service.TypeOfLeaveService;
import fr.enssat.leave_manager.service.exception.TypeOfLeaveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeOfLeaveServiceImpl implements TypeOfLeaveService {
    @Autowired
    private TypeOfLeaveRepository repository;

    @Override
    public boolean exists(String id) {
        return repository.existsById(id);
    }

    @Override
    public TypeOfLeaveEntity getTypeOfLeave(String id) {
        return repository.findById(id).orElseThrow(() -> new TypeOfLeaveException(id));
    }

    @Override
    public List<TypeOfLeaveEntity> getTypeOfLeaveByName(String name) {
        return repository.findByNameAndIsArchivedFalse(name);
    }

    @Override
    public List<TypeOfLeaveEntity> getTypeOfLeaves() {
        return repository.findAllByIsArchivedFalse();
    }

    public List<TypeOfLeaveEntity> getArchivedTypeOfLeaves() {
        return repository.findAllByIsArchivedTrue();
    }

    @Override
    public TypeOfLeaveEntity addTypeOfLeave(TypeOfLeaveEntity typeOfLeave) {
        return repository.save(typeOfLeave);
    }

    @Override
    public TypeOfLeaveEntity editTypeOfLeave(TypeOfLeaveEntity typeOfLeave) {
        return repository.save(typeOfLeave);
    }

    @Override
    public void deleteTypeOfLeave(String id) {
        // Soft delete
        TypeOfLeaveEntity type_of_leave = this.getTypeOfLeave(id);
        type_of_leave.setIsArchived(true);
        this.editTypeOfLeave(type_of_leave);
    }
}
