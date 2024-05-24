package ma.marjane.digitalisation_processus_recrutement.db1.service;

import ma.marjane.digitalisation_processus_recrutement.db1.dto.DirectionOuMagasinDto;

import java.util.Optional;
import java.util.UUID;

public interface DirectionOuMagasinService extends BaseService<DirectionOuMagasinDto, UUID>{

    Optional<DirectionOuMagasinDto> findById(UUID id);

    DirectionOuMagasinDto save(DirectionOuMagasinDto directionOuMagasinDto);

//    DirectionOuMagasinDto update(DirectionOuMagasinDto directionOuMagasinDto);

    void deleteById(UUID id);
}
