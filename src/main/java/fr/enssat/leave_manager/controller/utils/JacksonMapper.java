package fr.enssat.leave_manager.controller.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.enssat.leave_manager.model.DepartmentEntity;
import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.TeamEntity;

import java.util.List;

public class JacksonMapper {

    private final static ObjectMapper mapper = new ObjectMapper();

    public static String convertTeamToJSON(TeamEntity team) {

        try {
            return mapper.writeValueAsString(team);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "error";
    }

    public static String convertDepartmentsToJSON(List<DepartmentEntity> departmentList) {

        try {
            return mapper.writeValueAsString(departmentList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "error";
    }

    public static String convertEmployeesToJSON(List<EmployeeEntity> employeeList) {

        try {
            return mapper.writeValueAsString(employeeList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "error";
    }
}
