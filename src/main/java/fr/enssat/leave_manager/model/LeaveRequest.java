package fr.enssat.leave_manager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "LeaveRequest")
@EqualsAndHashCode @ToString
@RequiredArgsConstructor
@AllArgsConstructor @NoArgsConstructor
public class LeaveRequest implements Serializable {

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 33, updatable = false)
    @Getter @NonNull
    @Size(min = 33, max = 33)
    private String lrid;

    @Column(length = 255)
    @Getter @Setter
    @Size(max = 255, message = "La raison du congés ne doit pas dépasser les 255 caractères !")
    private String reason;

    @Column(nullable = false)
    @Getter @Setter @NonNull
    @PastOrPresent
    private LocalDateTime creation;

    @Column(nullable = false)
    @Getter @Setter @NonNull
    @PastOrPresent
    private LocalDateTime last_edition;

    @Column(nullable = false)
    @Getter @Setter @NonNull
    @FutureOrPresent
    private LocalDateTime starting_date;

    @Column(nullable = false)
    @Getter @Setter @NonNull
    @FutureOrPresent
    private LocalDateTime ending_date;

    @Column(length = 255)
    @Getter @Setter
    @Size(max = 255, message = "Le commentaire du RH ne doit pas dépasser les 225 caractères !")
    private String hr_comment;

    @Getter @Setter @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "eid", referencedColumnName = "eid")
    private Employee employee;

    @Getter @Setter @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "type_id", referencedColumnName = "id")
    private TypeOfLeave typeOfLeave;

    @Column(length = 16, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private LeaveStatus status;
}
