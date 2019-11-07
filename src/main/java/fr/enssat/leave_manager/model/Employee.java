package fr.enssat.leave_manager.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Employee {
    @Size(min = 1, max = 45, message = "Comment should be maximum 45 characters")
    private String lastname;
    @Size(min = 1, max = 45, message = "Comment should be maximum 45 characters")
    private String firstname;
    @Size(min = 1, max = 128, message = "Comment should be maximum 128 characters")
    private String street;
    @Size(min = 1, max = 128, message = "Comment should be maximum 128 characters")
    private String city;
    @Size(min = 1, max = 16, message = "Comment should be maximum 16 characters")
    private String post_code;
    @Size(min = 1, max = 128, message = "Comment should be maximum 128 characters")
    private String country;
    @Size(min = 1, max = 45, message = "Comment should be maximum 45 characters")
    private String position;
    @Min(value = 0, message = "Number of leaves must be greater or equal to 0")
    private Double remaining_leaves = 25.0;
    @Email
    private String email;
    @Size(min = 10, max = 30, message = "Password length must greater or equal to 10 and lower than 30 characters ")
    private String password;
    @Size(min = 29, max = 29)
    private String eid;
    @NotEmpty
    private List<Team> teamList = new ArrayList<Team>();
    @NotEmpty
    private List<LeaveRequest> leaveRequestList = new ArrayList<LeaveRequest>();

    public Employee(@Size(min = 1, max = 45, message = "Comment should be maximum 45 characters") String lastname, @Size(min = 1, max = 45, message = "Comment should be maximum 45 characters") String firstname, @Size(min = 1, max = 128, message = "Comment should be maximum 128 characters") String street, @Size(min = 1, max = 128, message = "Comment should be maximum 128 characters") String city, @Size(min = 1, max = 16, message = "Comment should be maximum 16 characters") String post_code, @Size(min = 1, max = 128, message = "Comment should be maximum 128 characters") String country, @Size(min = 1, max = 45, message = "Comment should be maximum 45 characters") String position, @Min(value = 0, message = "Number of leaves must be greater or equal to 0") Double remaining_leaves, @Email String email, @Size(min = 10, max = 30, message = "Password length must greater or equal to 10 and lower than 30 characters ") String password, @NotEmpty List<Team> teamList, @NotEmpty List<LeaveRequest> leaveRequestList) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.street = street;
        this.city = city;
        this.post_code = post_code;
        this.country = country;
        this.position = position;
        this.remaining_leaves = remaining_leaves;
        this.email = email;
        this.password = password;
        this.teamList = teamList;
        this.leaveRequestList = leaveRequestList;
    }
}
