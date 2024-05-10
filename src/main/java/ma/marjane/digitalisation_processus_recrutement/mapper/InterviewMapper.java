package ma.marjane.digitalisation_processus_recrutement.mapper;

import ma.marjane.digitalisation_processus_recrutement.dto.InterviewDto;
import ma.marjane.digitalisation_processus_recrutement.entity.Interview;

public interface InterviewMapper {

    Interview convertToEntity(InterviewDto interviewDto);
    InterviewDto convertToDto(Interview interview);

}
