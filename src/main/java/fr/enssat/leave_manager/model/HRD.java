package fr.enssat.leave_manager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "HRD")
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class HRD implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 29, updatable = false)
    @Getter @NonNull
    @Size(min = 29, max = 29)
    private String eid;

    @Getter @Setter @NonNull
    @OneToOne(optional = false, cascade = CascadeType.ALL, mappedBy = "hrd")
    @JoinColumn(name = "eid", nullable = false, referencedColumnName = "hrd")
    @MapsId
    private Employee employee;
}
