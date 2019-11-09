package fr.enssat.leave_manager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Team")
@EqualsAndHashCode @ToString
@RequiredArgsConstructor
@AllArgsConstructor @NoArgsConstructor
public class Team extends PKGenerator implements Serializable {
    @Id
    @Column(length = 25, updatable = false)
    @Getter
    @Size(min = 25, max = 25)
    private String id;

    @Column(length = 45)
    @Getter @Setter @NonNull
    @Size(min = 1, max = 45, message = "Le nom ne peut pas être vide et ne doit pas dépasser les 45 caractères !")
    private String name;

    @Getter @Setter
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "leader_id", referencedColumnName = "eid")
    private TeamLeader teamLeader;

    @Getter @Setter
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "dept_id", referencedColumnName = "id")
    private Department department;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @Getter @Setter
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "EmployeeTeam",
            joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "eid", referencedColumnName = "eid", nullable = false))
    private Set<Employee> employeeList;

    @Override
    public void setId() {
        if (this.id == null) this.id = this.generatePK("TEAM");
    }
}
