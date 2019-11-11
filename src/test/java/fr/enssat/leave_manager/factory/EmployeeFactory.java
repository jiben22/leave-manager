package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.Employee;
import fr.enssat.leave_manager.model.LeaveRequest;
import fr.enssat.leave_manager.model.Team;

import java.util.HashSet;
import java.util.Set;

public class EmployeeFactory {

    public static Employee getEmployee() {

        Set<Team> teams = new HashSet<>();
//        teams.add(TeamFactory.getTeam());
        Set<LeaveRequest> leaveRequests = new HashSet<>();
//        leaveRequests.add(LeaveRequestFactory.getLeaveRequest());

        Employee employee = new Employee();
        return employee.builder()
                .lastname("Captain")
                .firstname("America")
                .street("Man")
                .city("Manhattan")
                .post_code("10004")
                .country("United States")
                .position("Super héro")
                .email("captain.america@enssat.fr")
                .password("@Password99")
                .teamList(teams)
                .leaveRequestList(leaveRequests)
                .build();
    }
}
