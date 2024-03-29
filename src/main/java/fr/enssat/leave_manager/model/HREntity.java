package fr.enssat.leave_manager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "HR")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HREntity extends PKGenerator implements Serializable {
    @Id
    @Column(length = 29, updatable = false)
    @Size(min = 29, max = 29)
    @Builder.Default
    private String eid = PKGenerator.generatePK("EMPLOYEE");

    @NonNull
    @OneToOne(optional = false, mappedBy = "hr")
    @JoinColumn(name = "eid", nullable = false, unique = true, referencedColumnName = "hr")
    @MapsId("eid")
    private EmployeeEntity employee;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hr")
    private Set<LeaveRequestEntity> leaveRequests;

    @PreRemove
    private void preRemove() {
        // delete 'hr' field from employee table
        employee.setHr(null);
    }
}
