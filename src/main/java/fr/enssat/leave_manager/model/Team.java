package fr.enssat.leave_manager.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Team {
    @Size(min = 1, max = 45, message = "Comment should be maximum 45 characters")
    private String name;
    @NotNull
    private TeamLeader teamLeader;
    @NotNull
    private Department department;
    @Size(min = 25, max = 25)
    private String id;
    @NotEmpty
    private List<Employee> employeeList = new ArrayList<Employee>();


}
