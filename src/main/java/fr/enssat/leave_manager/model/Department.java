package fr.enssat.leave_manager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Department")
@EqualsAndHashCode @ToString
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ToString.Exclude
    @Getter
    @Size(min = 31, max = 31)
    private String id;

    @Column(name = "name")
    @Getter @Setter @NonNull
    @Size(min = 1, max = 45, message = "Le nom ne peut pas être vide et ne doit aps dépasser les 45 caractères !")
    private String name;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @Getter @Setter
    @NotNull
    private Set<Team> teams;
}
