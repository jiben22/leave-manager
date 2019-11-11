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
public class HRD extends PKGenerator implements Serializable {
    @Id
    @Column(length = 29, updatable = false)
    @Setter(AccessLevel.NONE)
    @Size(min = 29, max = 29)
    private String eid = this.generatePK("EMPLOYEE");

    @NonNull
    @OneToOne(optional = false, cascade = CascadeType.ALL, mappedBy = "hrd")
    @JoinColumn(name = "eid", nullable = false, referencedColumnName = "hrd")
    @MapsId
    private Employee employee;
}
