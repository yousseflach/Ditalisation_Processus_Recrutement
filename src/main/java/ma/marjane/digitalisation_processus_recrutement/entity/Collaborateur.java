package ma.marjane.digitalisation_processus_recrutement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Collaborateur extends Demande {

    @Column(name = "site_rattachment")
    private String siteRattachement; // Site de rattachement

    @Column(name = "creation_reason")
    private String motifCreation; // Motif de création

    @Column(name = "position_budgeted")
    private Boolean posteBudgete; // Budgétisation du poste

    @Column(name = "estimated_budget")
    private Number budgetEstime; // Estimation du budget

    @Column(name = "departure_reason")
    private String motifDepart; // Motif de départ

    @Column(name = "functional_supervisor")
    private String superviseurFonctionnel; // Supérieur fonctionnel

    private String relationsHierarchiques; // Relations hiérarchiques

    private String relationsFonctionnelles; // Relations fonctionnelles

    @Column(name = "mission_globale", length = 1024)
    private String missionGlobale; // Mission globale

    @Column(name = "principales_activités", length = 1024)
    private String principalesActivites; // Principales activités

    private String indicateursQuantitatifs; // Indicateurs quantitatifs

    private String indicateursQualitatifs; // Indicateurs qualitatifs

    @Column(name = "contract_type")
    private String typeContrat; // Type de contrat

    @Column(name = "Catégorie")
    private String categorie; // Catégorie

    @Column(name = "type_recruitment")
    private String typeRecrutement; // Type de recrutement


}
