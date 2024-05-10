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
public class CandidateDto {

    private UUID id;
    private String requestId;
    private String createur;
    private String cv;
    private LocalDateTime dateDeCreation;
    private LocalDateTime dateDeModification;

}
