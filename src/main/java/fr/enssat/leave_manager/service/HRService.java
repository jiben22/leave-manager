package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.HREntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface HRService {
    HREntity getHR(String id);
    HREntity addHR(HREntity hr);
    HREntity editHR(HREntity hr);
    void deleteHR(HREntity hr);
}
