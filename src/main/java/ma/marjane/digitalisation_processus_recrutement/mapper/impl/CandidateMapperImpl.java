package ma.marjane.digitalisation_processus_recrutement.mapper.impl;

import ma.marjane.digitalisation_processus_recrutement.dto.CandidateDto;
import ma.marjane.digitalisation_processus_recrutement.entity.Candidate;
import ma.marjane.digitalisation_processus_recrutement.mapper.BaseMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapperImpl implements BaseMapper<Candidate, CandidateDto> {

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public Candidate convertToEntity(CandidateDto candidateDto) {
        return modelMapper.map(candidateDto, Candidate.class);
    }

    @Override
    public CandidateDto convertToDto(Candidate candidateEntity) {
        return modelMapper.map(candidateEntity, CandidateDto.class);
    }
}
