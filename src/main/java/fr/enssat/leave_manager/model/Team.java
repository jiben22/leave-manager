package fr.enssat.leave_manager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Team")
@EqualsAndHashCode
@ToString
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ToString.Exclude
    @Getter
    @Size(min = 25, max = 25)
    private String id;

    @Column(name = "name")
    @Getter @Setter @NonNull
    @Size(min = 1, max = 45, message = "Le nom ne peut pas être vide et ne doit aps dépasser les 45 caractères !")
    private String name;

    @ManyToOne
    @JoinColumn
    @NotNull
    private TeamLeader teamLeader;

    @ManyToOne
    @JoinColumn
    @NotNull
    private Department department;

    @NotEmpty
    private List<Employee> employeeList = new ArrayList<Employee>();
}
