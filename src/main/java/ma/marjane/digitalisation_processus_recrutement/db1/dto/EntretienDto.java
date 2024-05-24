package ma.marjane.digitalisation_processus_recrutement.db1.dto;

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
public class EntretienDto {

    private UUID id;
    private String responsableId;
    private String candidatId;
    private String createur;
    private String evaluationDuCandidat;

    private LocalDateTime dateDeCreation;
    private LocalDateTime dateDeModification;
}