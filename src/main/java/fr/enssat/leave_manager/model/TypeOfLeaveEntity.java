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
@Builder
public class TypeOfLeaveEntity extends PKGenerator implements Serializable {
    @Id
    @Column(length = 32, updatable = false)
    @Size(min = 32, max = 32)
    @Builder.Default
    private String id = PKGenerator.generatePK("TYPEOFLEAVE");

    @Column(length = 45)
    @NonNull
    @Size(min = 1, max = 45, message = "Le nom ne peut pas être vide et ne doit pas dépasser les 45 caractères !")
    private String name;

    @Column
    private String description;

    @Column
    @NonNull
    @Builder.Default
    private Boolean isArchived = false;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "typeOfLeave")
    private Set<LeaveRequestEntity> leaveRequests;
}
