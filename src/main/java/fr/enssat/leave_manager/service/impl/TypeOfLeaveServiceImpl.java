package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.TypeOfLeave;
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
    public TypeOfLeave getTypeOfLeave(String id) {
        return repository.findById(id).orElseThrow(() -> new TypeOfLeaveException(id));
    }

    @Override
    public List<TypeOfLeave> getTypeOfLeaveByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<TypeOfLeave> getTypeOfLeaves() {
        return repository.findAll();
    }

    @Override
    public TypeOfLeave addTypeOfLeave(TypeOfLeave typeOfLeave) {
        return repository.save(typeOfLeave);
    }

    @Override
    public TypeOfLeave editTypeOfLeave(TypeOfLeave typeOfLeave) {
        return repository.save(typeOfLeave);
    }

    @Override
    public void deleteTypeOfLeave(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteTypeOfLeave(TypeOfLeave typeOfLeave) {
        repository.delete(typeOfLeave);
    }
}
