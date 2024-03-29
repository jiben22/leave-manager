package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.DepartmentEntity;
import fr.enssat.leave_manager.repository.DepartmentRepository;
import fr.enssat.leave_manager.service.DepartmentService;
import fr.enssat.leave_manager.service.exception.already_exists.DepartmentAlreadyExistsException;
import fr.enssat.leave_manager.service.exception.not_found.DepartmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    @Override
    public boolean exists(String id) {
        return repository.existsById(id);
    }

    @Override
    public DepartmentEntity getDepartment(String id) {
        return repository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    @Override
    public DepartmentEntity getDepartmentByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new DepartmentNotFoundException(name));
    }

    @Override
    public List<DepartmentEntity> getDepartments() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public DepartmentEntity addDepartment(DepartmentEntity department) {
        if (repository.existsById(department.getId()))
            throw new DepartmentAlreadyExistsException(department);
        return repository.saveAndFlush(department);
    }

    @Override
    public DepartmentEntity editDepartment(DepartmentEntity department) {
        if (!repository.existsById(department.getId()))
            throw new DepartmentNotFoundException(department.getId());
        return repository.saveAndFlush(department);
    }

    @Override
    public void deleteDepartment(String id) {
        repository.deleteById(id);
    }
}
