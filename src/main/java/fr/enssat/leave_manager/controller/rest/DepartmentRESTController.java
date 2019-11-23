package fr.enssat.leave_manager.controller.rest;

import fr.enssat.leave_manager.controller.utils.JacksonMapper;
import fr.enssat.leave_manager.model.DepartmentEntity;
import fr.enssat.leave_manager.service.DepartmentService;
import fr.enssat.leave_manager.service.impl.DepartmentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentRESTController {

    Logger logger = LoggerFactory.getLogger(DepartmentRESTController.class);

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentRESTController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/api/departments")
    public ResponseEntity<String> getDepartments() {

        logger.debug("GET /api/departments");

        List<DepartmentEntity> departmentList = departmentService.getDepartments();
        String jsonDepartments = JacksonMapper.convertDepartmentsToJSON(departmentList);
        return new ResponseEntity<>(jsonDepartments, HttpStatus.OK);
    }
}
