package fr.enssat.leave_manager.model;

import fr.enssat.leave_manager.utils.enums.LeaveStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "LeaveRequest")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LeaveRequestEntity extends PKGenerator implements Serializable {

    @Id
    @Column(length = 33, updatable = false)
    @Size(min = 33, max = 33)
    @Builder.Default
    private String lrid = PKGenerator.generatePK("LEAVEREQUEST");

    @Size(max = 255, message = "La raison du congés ne doit pas dépasser les 255 caractères !")
    private String reason;

    @Column(nullable = false)
    @NonNull
    @PastOrPresent
    @Builder.Default
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(nullable = false)
    @NonNull
    @PastOrPresent
    @Builder.Default
    private LocalDateTime lastEditionDate = LocalDateTime.now();

    @Column(nullable = false)
    @NonNull
    @FutureOrPresent
    private LocalDateTime startingDate;

    @Column(nullable = false)
    @NonNull
    @FutureOrPresent
    private LocalDateTime endingDate;

    @Size(max = 255, message = "Le commentaire du RH ne doit pas dépasser les 225 caractères !")
    private String hrComment;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "eid", referencedColumnName = "eid")
    private EmployeeEntity employee;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "type_id", referencedColumnName = "id")
    private TypeOfLeaveEntity typeOfLeave;

    @ManyToOne
    @JoinColumn(name = "hr_eid", referencedColumnName = "eid")
    @Builder.Default
    private HREntity hr = null;

    @Column(length = 16, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private LeaveStatus status;
}