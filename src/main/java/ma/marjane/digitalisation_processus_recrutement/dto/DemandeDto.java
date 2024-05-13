package ma.marjane.digitalisation_processus_recrutement.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.marjane.digitalisation_processus_recrutement.entity.Tache;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class DemandeDto {

    private UUID id;
    private String titre;
    private String type;
    private String siteRattachement;
    private String direction;
    private String magasin;
    private String titrePoste;
    private String superviseur;
    private String compétencesTechniques;
    private String compétencesManageriales;
    private LocalDateTime dateDeCreation;
    private LocalDateTime dateDeModification;
}
