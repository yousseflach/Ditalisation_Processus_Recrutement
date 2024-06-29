package ma.marjane.digitalisation_processus_recrutement.db1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ma.marjane.digitalisation_processus_recrutement.db1.enumeration.EntretienStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "entretiens")
public class Entretien {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;


    @Enumerated(EnumType.STRING)
    private EntretienStatus status=EntretienStatus.PLANIFIE;

    private int rate;

    @Column(name = "evaluation_du_candidat")
    private String evaluationDuCandidat;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private LocalDateTime dateEntretien;

    @ManyToOne
    @JoinColumn(name = "candidat_id", nullable = false)
    @JsonIgnore
    private Candidat candidat;  // Référence à la candidat associée

    @ManyToOne
    @JoinColumn(name = "createur_id", nullable = false)
    @JsonIgnore
    private Utilisateur createur;

    @ManyToOne
    @JoinColumn(name = "evaluateur_id", nullable = false)
    @JsonIgnore
    private Utilisateur evaluateur;
}
