package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.HREntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HRService {
    boolean exists(String id);
    HREntity getHR(String id);
    List<HREntity> getHRs();
    HREntity addHR(HREntity hr);
    void deleteHR(String eid);
}
