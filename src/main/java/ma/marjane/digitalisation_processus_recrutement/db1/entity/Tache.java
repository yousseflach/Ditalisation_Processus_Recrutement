package ma.marjane.digitalisation_processus_recrutement.db1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "taches")
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String etape;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateDeDebut;

    @ManyToOne
    @JoinColumn(name = "demande_id", nullable = false)
    @JsonIgnore
    private Demande demande;

}
