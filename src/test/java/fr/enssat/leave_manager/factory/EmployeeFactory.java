package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.LeaveRequestEntity;
import fr.enssat.leave_manager.model.TeamEntity;

import java.util.HashSet;
import java.util.Set;

public class EmployeeFactory {

    public static EmployeeEntity getEmployee() {

        Set<TeamEntity> teams = new HashSet<>();
//        teams.add(TeamFactory.getTeam());
        Set<LeaveRequestEntity> leaveRequests = new HashSet<>();
//        leaveRequests.add(LeaveRequestFactory.getLeaveRequest());

        return EmployeeEntity.builder()
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
