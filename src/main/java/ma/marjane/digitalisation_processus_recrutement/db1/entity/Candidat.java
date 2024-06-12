package ma.marjane.digitalisation_processus_recrutement.db1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import ma.marjane.digitalisation_processus_recrutement.db1.enumeration.StatutSelection;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "candidates")
@EntityListeners(AuditingEntityListener.class)
public class Candidat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String cvPath;
    private String commentaire;
    @Enumerated(EnumType.STRING)
    private StatutSelection statutSelection;

    @ManyToOne
    @JoinColumn(name = "demande_id")
    @JsonBackReference
    private Demande demande;
}
