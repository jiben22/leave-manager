package fr.enssat.leave_manager.controller.rest;

import fr.enssat.leave_manager.controller.utils.JacksonMapper;
import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.service.EmployeeService;
import fr.enssat.leave_manager.service.impl.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeRESTController {

    Logger logger = LoggerFactory.getLogger(EmployeeRESTController.class);

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRESTController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/api/employees")
    public ResponseEntity<String> getEmployees() {

        logger.debug("GET /api/employees");

        List<EmployeeEntity> employeeList = employeeService.getEmployees();
        String jsonEmployees = JacksonMapper.convertEmployeesToJSON(employeeList);
        return new ResponseEntity<>(jsonEmployees, HttpStatus.OK);
    }
}
