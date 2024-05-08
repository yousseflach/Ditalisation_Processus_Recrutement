package ma.marjane.digitalisation_processus_recrutement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;  // Utilisation d'un UUID pour l'ID si vous ne fournissez pas de spécifications de type de données spécifiques pour l'ID.

    private String titre;
    private String description;
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "directionOrStore_id")
    private DirectionOuMagasin directionOuMagasin; // Référence à la Direction ou Magasin associé.

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tâche> tâches;

    private String titrePoste;
    private String superviseur;
    private String formationCandidat;
    private String niveauÉducation;
    private String écolesPréférées;
    private String compétencesTechniques;
    private String compétencesManageriales;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateDeCreation;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime dateDeModification;


}
