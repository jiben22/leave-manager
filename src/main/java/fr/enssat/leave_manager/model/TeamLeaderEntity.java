package fr.enssat.leave_manager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "TeamLeader")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TeamLeaderEntity extends PKGenerator implements Serializable {
    @Id
    @Column(length = 29, updatable = false)
    @Setter(AccessLevel.NONE)
    @Size(min = 29, max = 29)
    @Builder.Default
    private String eid = PKGenerator.generatePK("EMPLOYEE");

    @NonNull
    @OneToOne(optional = false, mappedBy = "teamLeader")
    @JoinColumn(name = "eid", nullable = false, unique = true, referencedColumnName = "eid")
    @MapsId("eid")
    @JsonBackReference
    private EmployeeEntity employee;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamLeader")
    @JsonBackReference
    private Set<TeamEntity> teamList;

    @PreRemove
    private void preRemove() {
        // delete 'teamLeader' field from employee table
        employee.setTeamLeader(null);
    }
}
