package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.DepartmentEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface DepartmentService {
    boolean exists(String id);
    DepartmentEntity getDepartment(String id);
    DepartmentEntity getDepartmentByName(String name);
    List<DepartmentEntity> getDepartments();
    DepartmentEntity addDepartment(DepartmentEntity department);
    DepartmentEntity editDepartment(DepartmentEntity department);
    void deleteDepartment(String id);
}
