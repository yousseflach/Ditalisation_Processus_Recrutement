package ma.marjane.digitalisation_processus_recrutement.mapper;

import ma.marjane.digitalisation_processus_recrutement.dto.DirectionOuMagasinDto;
import ma.marjane.digitalisation_processus_recrutement.entity.DirectionOuMagasin;

public interface DirectionOuMagasinMapper {

    DirectionOuMagasin convertToEntity(DirectionOuMagasinDto directionOuMagasinDto);
    DirectionOuMagasinDto convertToDto(DirectionOuMagasin directionOuMagasin);
}
