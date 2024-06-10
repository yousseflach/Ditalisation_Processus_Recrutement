package ma.marjane.digitalisation_processus_recrutement.db1.service;

import ma.marjane.digitalisation_processus_recrutement.db1.dto.InterviewDto;

import java.util.Optional;
import java.util.UUID;

public interface InterviewService extends BaseService<InterviewDto, UUID>{

    Optional<InterviewDto> findById(UUID id);

    InterviewDto save(InterviewDto interviewDto);

//    InterviewDto update(InterviewDto interviewDto);

    void deleteById(UUID id);
}
