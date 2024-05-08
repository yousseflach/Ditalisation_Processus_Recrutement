package ma.marjane.digitalisation_processus_recrutement.dto;

import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

public class CandidatDto {
    private UUID id;
    private String requestId;

    private String createur;

    private String cv;

    private LocalDateTime dateDeCreation;

    private LocalDateTime dateDeModification;
}
