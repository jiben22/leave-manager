package fr.enssat.leave_manager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Team")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties({"id"})
public class TeamEntity extends PKGenerator implements Serializable {
    @Id
    @Column(length = 25, updatable = false)
    @Setter(AccessLevel.NONE)
    @Size(min = 25, max = 25)
    @Builder.Default
    private String id = PKGenerator.generatePK("TEAM");

    @Column(length = 45)
    @NonNull
    @Size(min = 1, max = 45, message = "Le nom ne peut pas être vide et ne doit pas dépasser les 45 caractères !")
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "leader_id", referencedColumnName = "eid")
    @JsonManagedReference
    private TeamLeaderEntity teamLeader;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "dept_id", referencedColumnName = "id")
    @JsonBackReference
    private DepartmentEntity department;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })
    @JoinTable(name = "EmployeeTeam",
            joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "eid", referencedColumnName = "eid", nullable = false))
    @JsonManagedReference
    private Set<EmployeeEntity> employeeList;
}
