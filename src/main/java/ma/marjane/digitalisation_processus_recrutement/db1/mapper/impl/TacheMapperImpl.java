package ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl;

import ma.marjane.digitalisation_processus_recrutement.db1.dto.TacheDto;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Tache;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.BaseMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TacheMapperImpl implements BaseMapper<Tache, TacheDto> {

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
