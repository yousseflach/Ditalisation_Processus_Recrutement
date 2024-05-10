package ma.marjane.digitalisation_processus_recrutement.mapper.impl;

import ma.marjane.digitalisation_processus_recrutement.dto.InterviewDto;
import ma.marjane.digitalisation_processus_recrutement.entity.Interview;
import ma.marjane.digitalisation_processus_recrutement.mapper.InterviewMapper;
import org.modelmapper.ModelMapper;

public class InterviewMapperImpl implements InterviewMapper {

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public Interview convertToEntity(InterviewDto interviewDto) {
        return modelMapper.map(interviewDto, Interview.class);
    }

    @Override
    public InterviewDto convertToDto(Interview interview) {
        return modelMapper.map(interview, InterviewDto.class);
    }
}
