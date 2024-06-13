package ma.marjane.digitalisation_processus_recrutement.db1.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "hierarchies")
public class Hierarchie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    private String matricule;

    private String nom;

    private String prenom;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date datedecreation;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "statut")
    private String statut = "En cours";

    @Column(name = "demande_id", nullable = false)
    private UUID demandeId;  // Référence à la demande associée
}
