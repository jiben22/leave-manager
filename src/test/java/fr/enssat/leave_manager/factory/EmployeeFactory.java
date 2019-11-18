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
                .postCode("10004")
                .country("United States")
                .position("Super héro")
                .email("captain.america@enssat.fr")
                .password("@Password99")
                .teamList(new HashSet<>())
                .leaveRequestList(new HashSet<>())
                .build();
    }

    public static EmployeeEntity getEmployee1() {
         return EmployeeEntity.builder()
            .eid("EMPLOYEE-157314099170606-0001")
            .firstname("Tony")
            .lastname("Stark")
            .street("9 rue du chene germain")
            .postCode("22700")
            .city("Lannion")
            .country("France")
            .email("tony.stark@marvel.com")
            .position("Director")
            .password("Ironman56789*")
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
                .postCode("22700")
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
                .postCode("0000")
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
                .postCode("0000")
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
                .postCode("000")
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
