package ma.marjane.digitalisation_processus_recrutement.db1.service;

import ma.marjane.digitalisation_processus_recrutement.db1.dto.EntretienDto;

import java.util.Optional;
import java.util.UUID;

public interface EntretienService extends BaseService<EntretienDto, UUID>{

    Optional<EntretienDto> findById(UUID id);

    EntretienDto save(EntretienDto interviewDto);

//    InterviewDto update(InterviewDto interviewDto);

    void deleteById(UUID id);
}
