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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    private String cvPath;

    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "demande_id")
    @JsonBackReference // Indique que cette entité est la partie "back" de la relation
    private Demande demande;

    @Enumerated(EnumType.STRING)
    private StatutSelection statutSelection; // Nouvelle propriété pour l'état de sélection
}
