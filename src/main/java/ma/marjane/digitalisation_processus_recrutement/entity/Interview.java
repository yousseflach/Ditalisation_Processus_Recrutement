package ma.marjane.digitalisation_processus_recrutement.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "interviews")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "responsable_id")
    private String responsableId;

    @Column(name = "candidat_id")
    private String candidatId;

    @Column(name = "createur")
    private String createur;

    @Column(name = "evaluation_du_candidat")
    private String evaluationDuCandidat;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateDeCreation;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime dateDeModification;
}