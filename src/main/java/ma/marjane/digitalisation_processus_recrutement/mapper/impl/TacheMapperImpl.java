package ma.marjane.digitalisation_processus_recrutement.mapper.impl;

import ma.marjane.digitalisation_processus_recrutement.dto.TacheDto;
import ma.marjane.digitalisation_processus_recrutement.entity.Tache;
import ma.marjane.digitalisation_processus_recrutement.mapper.TacheMapper;
import org.modelmapper.ModelMapper;

public class TacheMapperImpl implements TacheMapper {

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public Tache convertToEntity(TacheDto tacheDto) {
        return modelMapper.map(tacheDto, Tache.class);
    }

    @Override
    public TacheDto convertToDto(Tache tache) {
        return modelMapper.map(tache, TacheDto.class);
    }
}
