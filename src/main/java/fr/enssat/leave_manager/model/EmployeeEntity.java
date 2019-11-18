package fr.enssat.leave_manager.model;

import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
public class EmployeeEntity extends PKGenerator implements Serializable {
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

    // Override Lombok Setter to encode password
    public void setPassword(String password) {
        this.password = encodePassword(password);
    }

    // Override Builder 'password' function to encode password
    public static class EmployeeEntityBuilder {
        private String password;
        public EmployeeEntityBuilder password(String password) {
            this.password = encodePassword(password);
            return this;
        }
    }

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @NonNull
    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })
    @JoinTable(name = "EmployeeTeam",
            inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false),
            joinColumns = @JoinColumn(name = "eid", referencedColumnName = "eid", nullable = false))
    private Set<TeamEntity> teamList;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @NonNull
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<LeaveRequestEntity> leaveRequestList;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eid", referencedColumnName = "employee")
    private HREntity hr;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eid", referencedColumnName = "employee")
    private HRDEntity hrd;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eid", referencedColumnName = "employee")
    private TeamLeaderEntity teamLeader;

    public String getRole() {
        if (this.getHrd() != null) {
            return "HRD";
        } else if (this.getHr() != null) {
            return "HR";
        } else if (this.getTeamLeader() != null) {
            return "TEAMLEADER";
        } else {
            return "EMPLOYEE";
        }
    }
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // To encode password
    public static String encodePassword(@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{10,})", message = "Le mot de passe doit contenir au moins une majuscule, une minuscule, un nombre et un caractère spécial (!@#$%^&*). Il doit avoir une taille minimum de 10 caractères !") String password) {
        return encoder.encode(password);
    }

    // To check that the password match
    public boolean matchPassword(String password) {
        return encoder.matches(password, this.password);
    }
}