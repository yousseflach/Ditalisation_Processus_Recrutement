package ma.marjane.digitalisation_processus_recrutement.db1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "demande_collaborateurs")
public class Collaborateur extends Demande {

    @Column(name = "nature_de_recrutement")
    private String natureDeRecrutement;

    @Column(name = "is_budgeted")
    private Boolean posteBudgete;

    @Column(name = "motif")
    private String motif;

    @Column(name = "functional_supervisor")
    private String superviseurFonctionnel;

    @Column(name = "relations_hierarchiques")
    private String relationsHierarchiques;

    @Column(name = "relations_fonctionnelles")
    private String relationsFonctionnelles;

    private String relationsExterne;

    @Column(name = "mission_globale", length = 1024)
    private String missionGlobale;

    @Column(name = "principales_activites", length = 1024)
    private String principalesActivites;

    private String indicateurs;

    @Column(name = "contract_type")
    private String typeContrat;

    @Column(name = "categorie")
    private String categorie;

    @Column(name = "type_recrutement")
    private String typeRecrutement;

    @Column(name ="societe")
    private String societe;

    @Column(name ="direction_ou_magasin")
    private String directionoumagasin;

    @Column(name ="sous_direction")
    private String sousDirection;

    @Column(name ="niveau_detude")
    private String niveauDetude;
}
