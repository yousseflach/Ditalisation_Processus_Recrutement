package ma.marjane.digitalisation_processus_recrutement.db1.service;

import ma.marjane.digitalisation_processus_recrutement.db1.dto.StageDto;

import java.util.Optional;
import java.util.UUID;

public interface StageService extends BaseService<StageDto, UUID>{

    Optional<StageDto> findById(UUID id);

    StageDto save(StageDto stageDto);

//    StageDto update(StageDto stageDto);

    void deleteById(UUID id);
}
