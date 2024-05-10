package ma.marjane.digitalisation_processus_recrutement.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StageDto extends DemandeDto {

    private String natureDuStage;
    private Integer dureeDuStage;
    private String contenuDuStage;
    private String livrableAttendu;
    private LocalDateTime dateDeCreation;
    private LocalDateTime dateDeModification;
}
