package ma.marjane.digitalisation_processus_recrutement.mapper.impl;

import ma.marjane.digitalisation_processus_recrutement.dto.CandidateDto;
import ma.marjane.digitalisation_processus_recrutement.entity.Candidate;
import ma.marjane.digitalisation_processus_recrutement.mapper.CandidateMapper;
import org.modelmapper.ModelMapper;

public class CandidateMapperImpl implements CandidateMapper {

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
