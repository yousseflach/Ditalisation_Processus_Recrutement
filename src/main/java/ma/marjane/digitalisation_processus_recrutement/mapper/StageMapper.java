package ma.marjane.digitalisation_processus_recrutement.mapper;

import ma.marjane.digitalisation_processus_recrutement.dto.StageDto;
import ma.marjane.digitalisation_processus_recrutement.entity.Stage;

public interface StageMapper {

    Stage convertToEntity(StageDto stageDto);
    StageDto convertToDto(Stage stage);
}
