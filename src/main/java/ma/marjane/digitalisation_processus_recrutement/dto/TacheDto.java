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
public class TacheDto {

    private UUID id;
    private String objet;
    private String description;
    private String idDemande;
    private int step;
    private String messageN1;
    private String messageN2;
    private String messageCOMEX;

    private LocalDateTime dateDeCreation;
    private LocalDateTime dateDeModification;

}
