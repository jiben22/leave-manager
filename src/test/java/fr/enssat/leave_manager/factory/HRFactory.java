package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.HR;

public class HRFactory {

    public static HR getHR() {

        HR hr = new HR();
        return hr.builder()
                .employee(EmployeeFactory.getEmployee())
                .build();
    }
}
