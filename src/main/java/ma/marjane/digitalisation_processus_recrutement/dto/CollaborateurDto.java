package ma.marjane.digitalisation_processus_recrutement.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CollaborateurDto extends DemandeDto {

    private String siteRattachement;
    private String motifCreation;
    private Boolean posteBudgete;
    private Number budgetEstime;
    private String motifDepart;
    private String superviseurFonctionnel;
    private String relationsHierarchiques;
    private String relationsFonctionnelles;
    private String missionGlobale;
    private String principalesActivites;
    private String indicateursQuantitatifs;
    private String indicateursQualitatifs;
    private String typeContrat;
    private String categorie;
    private String typeRecrutement;

}
