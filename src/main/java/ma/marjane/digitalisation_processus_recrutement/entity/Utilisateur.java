package ma.marjane.digitalisation_processus_recrutement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Utilisateur {

    @Id
    @Column(unique = true, nullable = false)
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

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateDeCreation;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime dateDeModification;

}
