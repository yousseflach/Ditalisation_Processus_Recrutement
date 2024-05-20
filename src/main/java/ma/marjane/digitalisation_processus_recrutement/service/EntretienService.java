package ma.marjane.digitalisation_processus_recrutement.service;

import ma.marjane.digitalisation_processus_recrutement.dto.EntretienDto;

import java.util.Optional;
import java.util.UUID;

public interface EntretienService extends BaseService<EntretienDto, UUID>{

    Optional<EntretienDto> findById(UUID id);

    EntretienDto save(EntretienDto interviewDto);

//    InterviewDto update(InterviewDto interviewDto);

    void deleteById(UUID id);
}
