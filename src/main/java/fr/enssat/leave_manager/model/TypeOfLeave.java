package fr.enssat.leave_manager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Team")
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@AllArgsConstructor @NoArgsConstructor
public class TypeOfLeave implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 32, updatable = false)
    @Getter @NonNull
    @Size(min = 32, max = 32)
    private String id;

    @Column(length = 45)
    @Getter @Setter @NonNull
    @Size(min = 1, max = 45, message = "Le nom ne peut pas être vide et ne doit pas dépasser les 45 caractères !")
    private String name;

    @Column
    @Getter @Setter
    private String desc;

    @Column
    @Getter @Setter @NonNull
    private Boolean is_archived;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "typeOfLeave", cascade = CascadeType.ALL)
    @Getter @Setter @NonNull
    private Set<LeaveRequest> leaveRequests;
}
