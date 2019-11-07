package fr.enssat.leave_manager.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class HRD extends HR implements ILeadership {
    public HRD(@Size(min = 1, max = 45, message = "Comment should be maximum 45 characters") String lastname, @Size(min = 1, max = 45, message = "Comment should be maximum 45 characters") String firstname, @Size(min = 1, max = 128, message = "Comment should be maximum 128 characters") String street, @Size(min = 1, max = 128, message = "Comment should be maximum 128 characters") String city, @Size(min = 1, max = 16, message = "Comment should be maximum 16 characters") String post_code, @Size(min = 1, max = 128, message = "Comment should be maximum 128 characters") String country, @Size(min = 1, max = 45, message = "Comment should be maximum 45 characters") String position, @Min(value = 0, message = "Number of leaves must be greater or equal to 0") Double remaining_leaves, @Email String email, @Size(min = 10, max = 30, message = "Password length must greater or equal to 10 and lower than 30 characters ") String password, @NotEmpty List<Team> teamList, @NotEmpty List<LeaveRequest> leaveRequestList) {
        super(lastname, firstname, street, city, post_code, country, position, remaining_leaves, email, password, teamList, leaveRequestList);
    }
}
