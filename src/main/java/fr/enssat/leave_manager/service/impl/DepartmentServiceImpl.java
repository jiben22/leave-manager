package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.Department;
import fr.enssat.leave_manager.repository.DepartmentRepository;
import fr.enssat.leave_manager.service.DepartmentService;
import fr.enssat.leave_manager.service.exception.DepartmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    @Override
    public Department getDepartment(String id) {
        return repository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    @Override
    public Department getDepartmentByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new DepartmentNotFoundException(name));
    }

    @Override
    public List<Department> getDepartments() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public Department addDepartment(Department department) {
        return repository.save(department);
    }

    @Override
    public Department editDepartment(Department department) {
        return repository.save(department);
    }

    @Override
    public void deleteDepartment(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteDepartment(Department department) {
        repository.delete(department);
    }
}
