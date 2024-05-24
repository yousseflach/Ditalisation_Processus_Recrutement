package ma.marjane.digitalisation_processus_recrutement.db1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDto {

    private String nom;
    private String prenom;
    private String uniteOrganisationnelle;
    private String direction;
    private String enseigneGroupe;
    private String adresseEmail;
    private String matriculeN1;
    private String matriculeN2;
    private String matriculeCOMEX;

    private LocalDateTime dateDeCreation;
    private LocalDateTime dateDeModification;
}
