package ma.marjane.digitalisation_processus_recrutement.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDto {
    private String matricule; // Matricule
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
