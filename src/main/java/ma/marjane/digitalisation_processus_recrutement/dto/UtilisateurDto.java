package ma.marjane.digitalisation_processus_recrutement.dto;

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
    private String id; // Matricule
    private String nom; // Nom
    private String prenom; // Prénom
    private String uniteOrganisationnelle; // Unité organisationnelle
    private String direction; // Direction
    private String enseigneGroupe; // Enseigne groupe
    private String adresseEmail; // Adresse email
    private String matriculeN1; // Matricule N1
    private String matriculeN2; // Matricule N2
    private String matriculeCOMEX; // Matricule COMEX
    private LocalDateTime dateDeCreation;
    private LocalDateTime dateDeModification;
}
