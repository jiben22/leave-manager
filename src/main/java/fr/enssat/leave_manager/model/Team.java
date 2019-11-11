package fr.enssat.leave_manager.model;

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
public class Team extends PKGenerator implements Serializable {
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
    private TeamLeader teamLeader;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "dept_id", referencedColumnName = "id")
    private Department department;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "EmployeeTeam",
            joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "eid", referencedColumnName = "eid", nullable = false))
    private Set<Employee> employeeList;
}
