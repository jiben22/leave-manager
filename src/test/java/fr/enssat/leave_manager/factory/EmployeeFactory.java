package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.EmployeeEntity;

import java.util.HashSet;

public class EmployeeFactory {

    public static EmployeeEntity getEmployee() {
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
                .teamList(new HashSet<>())
                .leaveRequestList(new HashSet<>())
                .build();
    }

    public static EmployeeEntity getEmployee2() {
        return EmployeeEntity.builder()
                .eid("EMPLOYEE-157314099170606-0002")
                .firstname("Thor")
                .lastname("Odinson")
                .street("5 avenue Asgardian")
                .post_code("22700")
                .city("Lannion")
                .country("France")
                .email("thor@marvel.com")
                .position("God")
                .password("Thor56789*")
                .teamList(new HashSet<>())
                .leaveRequestList(new HashSet<>())
                .build();
    }

    public static EmployeeEntity getEmployee3() {
        return EmployeeEntity.builder()
                .eid("EMPLOYEE-157314099170606-0003")
                .firstname("Henry")
                .lastname("Jonathan")
                .street("rue")
                .post_code("0000")
                .city("Nebraska")
                .country("USA")
                .email("antman@marvel.com")
                .position("Ant-man")
                .password("Antman123*")
                .teamList(new HashSet<>())
                .leaveRequestList(new HashSet<>())
                .build();
    }

    public static EmployeeEntity getEmployee5() {
        return EmployeeEntity.builder()
                .eid("EMPLOYEE-157314099170606-0005")
                .firstname("Steve")
                .lastname("Rogers")
                .street("0 rue du pole nord")
                .post_code("0000")
                .city("PoleNord")
                .country("Danemark")
                .email("captain@marvel.com")
                .position("Captain America")
                .password("Captain12*")
                .teamList(new HashSet<>())
                .leaveRequestList(new HashSet<>())
                .build();
    }

    public static EmployeeEntity getEmployee10() {
        return EmployeeEntity.builder()
                .eid("EMPLOYEE-157314099170606-0010")
                .firstname("Peter")
                .lastname("Parker")
                .street("quelque part")
                .post_code("000")
                .city("New York")
                .country("USA")
                .email("spiderman@marvel.com")
                .position("Spider Man")
                .password("Spiderman1*")
                .teamList(new HashSet<>())
                .leaveRequestList(new HashSet<>())
                .build();
    }
}
