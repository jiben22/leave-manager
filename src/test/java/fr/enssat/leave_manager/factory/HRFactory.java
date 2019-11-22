package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.HREntity;

public class HRFactory {

    public static HREntity getHR() {
        return HREntity.builder()
                .employee(EmployeeFactory.getEmployee())
                .build();
    }

    public static HREntity getHR10() {
        EmployeeEntity emp10 = EmployeeFactory.getEmployee10();
        return HREntity.builder()
                .eid(emp10.getEid())
                .employee(emp10)
                .build();
    }
}
