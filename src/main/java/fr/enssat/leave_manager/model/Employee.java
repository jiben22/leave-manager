package fr.enssat.leave_manager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "Employee")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Employee extends PKGenerator implements Serializable {
    @Id
    @Column(length = 29, updatable = false)
    @Setter(AccessLevel.NONE)
    @NonNull
    @Size(min = 29, max = 29)
    @Builder.Default
    private String eid = PKGenerator.generatePK("EMPLOYEE");

    @Column(nullable = false, length = 45)
    @NonNull
    @Size(min = 1, max = 45, message = "Le nom ne peut pas être vide et ne doit pas dépasser les 45 caractères !")
    private String lastname;

    @Column(nullable = false, length = 45)
    @NonNull
    @Size(min = 1, max = 45, message = "Le prenom ne peut pas être vide et ne doit pas dépasser les 45 caractères !")
    private String firstname;

    @Column(nullable = false, length = 128)
    @NonNull
    @Size(min = 1, max = 128, message = "La rue ne peut pas être vide et ne doit pas dépasser les 128 caractères !")
    private String street;

    @Column(nullable = false, length = 128)
    @NonNull
    @Size(min = 1, max = 128, message = "Le nom de la ville ne peut pas être vide et ne doit pas dépasser les 128 caractères !")
    private String city;

    @Column(nullable = false, length = 16)
    @NonNull
    @Size(min = 1, max = 16, message = "Le code postale ne peut pas être vide et ne doit pas dépasser les 16 caractères !")
    private String post_code;

    @Column(nullable = false, length = 128)
    @NonNull
    @Size(min = 1, max = 128, message = "Le pays ne peut pas être vide et ne doit pas dépasser les 128 caractères !")
    private String country;

    @Column(nullable = false, length = 45)
    @Setter(AccessLevel.NONE)
    @Size(max = 45, message = "La fonction ne doit pas dépasser les 45 caractères !")
    private String position;

    @Column(nullable = false)
    @NonNull
    @Min(value = 0, message = "Le nombre de congés doit être supérieur ou égale à zero!")
    @Builder.Default
    private Double remaining_leave = 25.0;

    @Column(nullable = false, length = 128, unique = true)
    @NonNull
    @Email
    private String email;

    @ToString.Exclude
    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    @NonNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{10,})", message = "Le mot de passe doit contenir au moins une majuscule, une minuscule, un nombre et un caractère spécial (!@#$%^&*). Il doit avoir une taille minimum de 10 caractères !")
    private String password;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @NonNull
    @ManyToMany(mappedBy = "employeeList")
    private Set<Team> teamList;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @NonNull
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<LeaveRequest> leaveRequestList;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.NONE)
    @OneToOne
    @JoinColumn(name = "eid", referencedColumnName = "employee")
    private HR hr;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.NONE)
    @OneToOne
    @JoinColumn(name = "eid", referencedColumnName = "employee")
    private HRD hrd;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.NONE)
    @OneToOne
    @JoinColumn(name = "eid", referencedColumnName = "employee")
    private TeamLeader teamLeader;
}