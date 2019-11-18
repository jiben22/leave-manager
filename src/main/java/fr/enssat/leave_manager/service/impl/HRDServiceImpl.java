package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.HRDEntity;
import fr.enssat.leave_manager.repository.HRDRepository;
import fr.enssat.leave_manager.service.HRDService;
import fr.enssat.leave_manager.service.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HRDServiceImpl implements HRDService {
    @Autowired
    private HRDRepository repository;

    @Override
    public boolean exists(String id) {
        return repository.existsById(id);
    }

    public HRDEntity getHRD(String id) {
        return this.repository.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
    }

    public HRDEntity addHRD(HRDEntity hrd) {
        return this.repository.save(hrd);
    }

    @Override
    public HRDEntity editHRD(HRDEntity hrd) {
        return this.repository.save(hrd);
    }

    @Override
    public void deleteHRD(String eid) {
        this.repository.deleteById(eid);
    }

}
