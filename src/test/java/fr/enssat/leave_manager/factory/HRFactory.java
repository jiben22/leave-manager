package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.HREntity;

public class HRFactory {

    public static HREntity getHR() {

        return HREntity.builder()
                .employee(EmployeeFactory.getEmployee())
                .build();
    }
}
