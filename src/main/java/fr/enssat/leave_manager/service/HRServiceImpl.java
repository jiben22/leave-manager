package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.repository.HRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HRServiceImpl implements TypeOfLeaveService {

    private final HRRepository hrRepository;

    @Autowired
    public HRServiceImpl(HRRepository hrRepository) {
        this.hrRepository = hrRepository;
    }
}
