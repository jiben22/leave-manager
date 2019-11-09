package fr.enssat.leave_manager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "HRD")
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class TeamLeader implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 29, updatable = false)
    @Getter @NonNull
    @Size(min = 29, max = 29)
    private String eid;

    @Getter @Setter @NonNull
    @OneToOne(optional = false, cascade = CascadeType.ALL, mappedBy = "teamLeader")
    @JoinColumn(name = "eid", nullable = false, referencedColumnName = "teamLeader")
    @MapsId
    private Employee employee;

    @Getter @Setter @NonNull
    @ToString.Exclude @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    @NotEmpty
    private Set<Team> teamList;
}
