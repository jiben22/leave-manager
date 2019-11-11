package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.Employee;

public class EmployeeFactory {

    public static Employee getEmployee() {

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
//                .teamList()
//                .leaveRequestList()
                .build();
    }
}
