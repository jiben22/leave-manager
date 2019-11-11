package fr.enssat.leave_manager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "TypeOfLeave")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TypeOfLeave extends PKGenerator implements Serializable {
    @Id
    @Column(length = 32, updatable = false)
    @Setter(AccessLevel.NONE)
    @Size(min = 32, max = 32)
    private String id = this.generatePK("TYPEOFLEAVE");

    @Column(length = 45)
    @NonNull
    @Size(min = 1, max = 45, message = "Le nom ne peut pas être vide et ne doit pas dépasser les 45 caractères !")
    private String name;

    @Column
    private String description;

    @Column
    @NonNull
    private Boolean is_archived;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "typeOfLeave", cascade = CascadeType.ALL)
    @NonNull
    private Set<LeaveRequest> leaveRequests;
}
