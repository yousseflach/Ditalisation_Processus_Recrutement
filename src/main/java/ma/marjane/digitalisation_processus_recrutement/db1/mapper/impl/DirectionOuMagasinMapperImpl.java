package ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl;

import ma.marjane.digitalisation_processus_recrutement.db1.dto.DirectionOuMagasinDto;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.DirectionOuMagasin;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.BaseMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DirectionOuMagasinMapperImpl implements BaseMapper<DirectionOuMagasin, DirectionOuMagasinDto> {

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public DirectionOuMagasin convertToEntity(DirectionOuMagasinDto directionOuMagasinDto) {
        return modelMapper.map(directionOuMagasinDto, DirectionOuMagasin.class);
    }

    @Override
    public DirectionOuMagasinDto convertToDto(DirectionOuMagasin directionOuMagasin) {
        return modelMapper.map(directionOuMagasin, DirectionOuMagasinDto.class);
    }
}
