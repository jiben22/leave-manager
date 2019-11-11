package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.Department;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface DepartmentService {

    @Transactional
    Department addDepartment(Department department);
}
