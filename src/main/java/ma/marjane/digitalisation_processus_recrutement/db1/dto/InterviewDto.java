package ma.marjane.digitalisation_processus_recrutement.db1.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InterviewDto {
    private String responsableId;
    private String candidatId;
    private String createur;
    private String evaluationDuCandidat;

    private LocalDateTime dateDeCreation;
    private LocalDateTime dateDeModification;
}
