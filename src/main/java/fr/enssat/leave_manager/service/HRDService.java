package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.HRDEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HRDService {
    boolean exists(String id);
    HRDEntity getHRD(String id);
    List<HRDEntity> getHRDs();
    HRDEntity addHRD(HRDEntity hrd);
    void deleteHRD(String eid);
}
