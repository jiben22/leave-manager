package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.repository.EmployeeRepository;
import fr.enssat.leave_manager.service.EmployeeService;
import fr.enssat.leave_manager.service.exception.already_exists.EmployeeAlreadyExistException;
import fr.enssat.leave_manager.service.exception.not_found.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    @Override
    public boolean exists(String id) {
        return repository.existsById(id);
    }

    @Override
    public EmployeeEntity getEmployee(String id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public EmployeeEntity getEmployeeByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new EmployeeNotFoundException(email));
    }

    @Override
    public List<EmployeeEntity> getEmployeeByFirstname(String firstname) {
        return repository.findByFirstname(firstname);
    }

    @Override
    public List<EmployeeEntity> getEmployeeByLastname(String lastname) {
        return repository.findByLastname(lastname);
    }

    @Override
    public List<EmployeeEntity> getEmployees() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "lastname"));
    }

    @Override
    public EmployeeEntity addEmployee(EmployeeEntity employee) {
        if (repository.existsById(employee.getEid()))
            throw new EmployeeAlreadyExistException(employee);
        return repository.saveAndFlush(employee);
    }

    @Override
    public EmployeeEntity editEmployee(EmployeeEntity employee) {
        if (!repository.existsById(employee.getEid()))
            throw new EmployeeNotFoundException(employee.getEid());
        return repository.saveAndFlush(employee);
    }

    @Override
    public void deleteEmployee(String id) {
        repository.deleteById(id);
    }
}