package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.HRD;

public class HRDFactory {

    public static HRD getHRD() {

        HRD hrd = new HRD();
        return hrd.builder()
                .employee(EmployeeFactory.getEmployee())
                .build();
    }
}
