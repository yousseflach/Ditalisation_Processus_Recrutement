package ma.marjane.digitalisation_processus_recrutement.dto;

import jakarta.persistence.Column;

public class CollaborateurDto {
    private String siteRattachement; // Site de rattachement
    private String motifCreation; // Motif de création
    private Boolean posteBudgete; // Budgétisation du poste
    private Number budgetEstime; // Estimation du budget
    private String motifDepart; // Motif de départ
    private String superviseurFonctionnel; // Supérieur fonctionnel

    private String relationsHierarchiques; // Relations hiérarchiques

    private String relationsFonctionnelles; // Relations fonctionnelles
    private String missionGlobale; // Mission globale
    private String principalesActivites; // Principales activités

    private String indicateursQuantitatifs; // Indicateurs quantitatifs

    private String indicateursQualitatifs; // Indicateurs qualitatifs

    private String typeContrat; // Type de contrat

    private String categorie; // Catégorie

    private String typeRecrutement; // Type de recrutement
}
