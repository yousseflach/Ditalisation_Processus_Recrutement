package ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl;

import ma.marjane.digitalisation_processus_recrutement.db1.dto.CandidatDto;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Candidat;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.BaseMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapperImpl implements BaseMapper<Candidat, CandidatDto> {

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public Candidat convertToEntity(CandidatDto candidateDto) {
        return modelMapper.map(candidateDto, Candidat.class);
    }

    @Override
    public CandidatDto convertToDto(Candidat candidateEntity) {
        return modelMapper.map(candidateEntity, CandidatDto.class);
    }
}
