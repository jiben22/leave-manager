package fr.enssat.leave_manager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "HRD")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HRDEntity extends PKGenerator implements Serializable {
    @Id
    @Column(length = 29, updatable = false)
    @Size(min = 29, max = 29)
    @Builder.Default
    private String eid = PKGenerator.generatePK("EMPLOYEE");

    @NonNull
    @OneToOne(optional = false, mappedBy = "hrd")
    @JoinColumn(name = "eid", nullable = false, unique = true, referencedColumnName = "hrd")
    @MapsId("eid")
    private EmployeeEntity employee;

    @PreRemove
    private void preRemove() {
        // delete 'hrd' field from employee table
        employee.setHrd(null);
    }
}
