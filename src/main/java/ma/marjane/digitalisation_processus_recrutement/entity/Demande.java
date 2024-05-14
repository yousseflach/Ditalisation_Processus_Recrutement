package ma.marjane.digitalisation_processus_recrutement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners(AuditingEntityListener.class)
public abstract class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;  // Utilisation d'un UUID pour l'ID si vous ne fournissez pas de spécifications de type de données spécifiques pour l'ID.

    private String titre;
    private String type;

    private String siteRattachement;
    private String direction; // Référence à la Direction ou Magasin associé.Magasin
    private String magasin;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tache> taches;

    private String titrePoste;
    private String superviseur;
    private String competencesTechniques;
    private String competencesManageriales;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateDeCreation;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime dateDeModification;


}
