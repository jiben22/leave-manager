package fr.enssat.leave_manager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "HR")
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor @NoArgsConstructor
public class HR implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 29, updatable = false)
    @Getter @NonNull
    @Size(min = 29, max = 29)
    private String eid;

    @Getter @Setter @NonNull
    @OneToOne(optional = false, cascade = CascadeType.ALL, mappedBy = "hr")
    @JoinColumn(name = "eid", nullable = false, referencedColumnName = "hr")
    @MapsId
    private Employee employee;
}
