package ma.marjane.digitalisation_processus_recrutement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class DemandeDto {

    private String titre;
    private String type;
    private String siteRattachement;
    private String direction;
    private String magasin;
    private String titrePoste;
    private String superviseur;
    private String competencesTechniques;
    private String competencesManageriales;

    private LocalDateTime dateDeCreation;
    private LocalDateTime dateDeModification;
}
