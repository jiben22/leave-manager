package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.HRDEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface HRDService {
    HRDEntity getHRD(String id);
    HRDEntity addHRD(HRDEntity hrd);
    HRDEntity editHRD(HRDEntity hrd);
    void deleteHRD(HRDEntity hrd);
}
