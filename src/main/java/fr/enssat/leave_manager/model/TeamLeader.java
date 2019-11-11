package fr.enssat.leave_manager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "TeamLeader")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeamLeader extends PKGenerator implements Serializable {
    @Id
    @Column(length = 29, updatable = false)
    @Setter(AccessLevel.NONE)
    @Size(min = 29, max = 29)
    private String eid = this.generatePK("EMPLOYEE");

    @NonNull
    @OneToOne(optional = false, cascade = CascadeType.ALL, mappedBy = "teamLeader")
    @JoinColumn(name = "eid", nullable = false, unique = true, referencedColumnName = "eid")
    @MapsId("eid")
    private Employee employee;

    @NonNull
    @ToString.Exclude @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamLeader")
    @NotEmpty
    private Set<Team> teamList;
}
