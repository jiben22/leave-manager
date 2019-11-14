package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.HREntity;
import fr.enssat.leave_manager.repository.HRRepository;
import fr.enssat.leave_manager.service.HRService;
import fr.enssat.leave_manager.service.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HRServiceImpl implements HRService {

    private final HRRepository repository;

    @Autowired
    public HRServiceImpl(HRRepository repository) {
        this.repository = repository;
    }

    @Override
    public HREntity getHR(String id) {
        return this.repository.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
    }

    @Override
    public HREntity addHR(HREntity hr) {
        return this.repository.save(hr);
    }

    @Override
    public HREntity editHR(HREntity hr) {
        return this.repository.save(hr);
    }

    @Override
    public void deleteHR(HREntity hr) {
        this.repository.delete(hr);
    }
}
