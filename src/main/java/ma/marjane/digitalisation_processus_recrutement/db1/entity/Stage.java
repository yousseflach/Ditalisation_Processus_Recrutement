package ma.marjane.digitalisation_processus_recrutement.db1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "demande_stagaires")
public class Stage extends Demande {

    private String stageType;
    private String formationCandidat;
    private String niveauEducation;
    private String ecolesPreferees;

    @Column(name = "nature_of_internship") // Mappe le champ sur le nom de colonne spécifié dans la base de données.
    private String natureDuStage; // Nature du stage

    @Column(name = "duration_of_internship")
    private Integer dureeDuStage; // Durée du stage

    private LocalDateTime dateDeDebut;

    @Column(name = "content_of_internship", length = 1024) // Définition de la colonne spécifiée.
    private String contenuDuStage; // Contenu du stage

    @Column(name = "expected_deliverable", length = 1024)
    private String livrableAttendu; // Livrable attendu

}