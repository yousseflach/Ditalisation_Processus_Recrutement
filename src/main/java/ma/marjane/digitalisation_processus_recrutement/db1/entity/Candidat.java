package ma.marjane.digitalisation_processus_recrutement.db1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ma.marjane.digitalisation_processus_recrutement.db1.enumeration.StatutSelection;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
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

//    @ManyToOne
//    @JoinColumn(name = "demande_id")
//    @JsonBackReference
//    private Demande demande;

    @ManyToOne
    @JoinColumn(name = "demande_id", nullable = false)
    @JsonIgnore
    private Demande demande;  // Référence à la demande associée

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "candidat_id")
    private List<Entretien> entretiens;
}
