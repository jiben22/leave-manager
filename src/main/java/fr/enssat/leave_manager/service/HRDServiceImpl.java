package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.repository.HRDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HRDServiceImpl implements TypeOfLeaveService {

    private final HRDRepository hrdRepository;

    @Autowired
    public HRDServiceImpl(HRDRepository hrdRepository) {
        this.hrdRepository = hrdRepository;
    }
}
