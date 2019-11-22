package fr.enssat.leave_manager.factory;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.HRDEntity;

public class HRDFactory {

    public static HRDEntity getHRD() {

        return HRDEntity.builder()
                .employee(EmployeeFactory.getEmployee())
                .build();
    }

    public static HRDEntity getHRD5() {
        // he is HR and TeamLeader
        EmployeeEntity emp5 = EmployeeFactory.getEmployee5();
        return HRDEntity.builder()
                .eid(emp5.getEid())
                .employee(emp5)
                .build();
    }
}
