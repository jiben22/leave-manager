package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.HRDEntity;

public class HRDFactory {

    public static HRDEntity getHRD() {

        return HRDEntity.builder()
                .employee(EmployeeFactory.getEmployee())
                .build();
    }
}
