package ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl;

import ma.marjane.digitalisation_processus_recrutement.db1.dto.InterviewDto;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Interview;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.BaseMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class InterviewMapperImpl implements BaseMapper<Interview, InterviewDto> {

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
