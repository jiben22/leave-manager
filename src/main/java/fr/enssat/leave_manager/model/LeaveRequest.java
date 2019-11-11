package fr.enssat.leave_manager.model;

import lombok.*;

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
public class LeaveRequest extends PKGenerator implements Serializable {

    private enum LeaveStatus {
        PENDING("En attente"),
        CANCELLED("Annulé"),
        ACCEPTED("Accepté"),
        DECLINED("Refusé");

        private String fr;

        LeaveStatus(String fr) {
            this.fr = fr;
        }

        public String toString(){
            return this.fr;
        }
    }
    @Id
    @Column(length = 33, updatable = false)
    @Setter(AccessLevel.NONE)
    @Size(min = 33, max = 33)
    private String lrid = this.generatePK("LEAVEREQUEST");

    @Size(max = 255, message = "La raison du congés ne doit pas dépasser les 255 caractères !")
    private String reason;

    @Column(nullable = false)
    @NonNull
    @PastOrPresent
    private LocalDateTime creation = new Timestamp(System.currentTimeMillis()).toLocalDateTime();

    @Column(nullable = false)
    @NonNull
    @PastOrPresent
    private LocalDateTime last_edition = new Timestamp(System.currentTimeMillis()).toLocalDateTime();

    @Column(nullable = false)
    @NonNull
    @FutureOrPresent
    private LocalDateTime starting_date;

    @Column(nullable = false)
    @NonNull
    @FutureOrPresent
    private LocalDateTime ending_date;

    @Column(length = 255)
    @Size(max = 255, message = "Le commentaire du RH ne doit pas dépasser les 225 caractères !")
    private String hr_comment;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "eid", referencedColumnName = "eid")
    private Employee employee;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "type_id", referencedColumnName = "id")
    private TypeOfLeave typeOfLeave;

    @Column(length = 16, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private LeaveStatus status;
}