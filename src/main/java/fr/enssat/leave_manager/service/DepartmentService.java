package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.Department;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface DepartmentService {
    Department getDepartment(String id);
    Department getDepartmentByName(String name);
    List<Department> getDepartments();
    Department addDepartment(Department department);
    Department editDepartment(Department department);
    void deleteDepartment(String id);
    void deleteDepartment(Department department);
}
