package fr.enssat.leave_manager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Department")
@EqualsAndHashCode @ToString
@RequiredArgsConstructor
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length=31, updatable = false)
    @Getter @NonNull
    @Size(min = 31, max = 31)
    private String id;

    @Column(unique = true, nullable = false, length=45)
    @Getter @Setter @NonNull
    @Size(min = 1, max = 45, message = "Le nom ne peut pas être vide et ne doit pas dépasser les 45 caractères !")
    private String name;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    @Getter @Setter @NonNull
    private Set<Team> teamList;
}
