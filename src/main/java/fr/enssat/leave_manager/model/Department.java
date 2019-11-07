package fr.enssat.leave_manager.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Department {
    @Size(min = 1, max = 45, message = "Comment should be maximum 45 characters")
    private String name;
    @NotNull
    private List<Team> teams = new ArrayList<Team>();
    @Size(min = 31, max = 31)
    private String id;
}
