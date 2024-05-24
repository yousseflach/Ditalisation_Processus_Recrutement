package ma.marjane.digitalisation_processus_recrutement.db1.service;

import ma.marjane.digitalisation_processus_recrutement.db1.dto.CandidatDto;

import java.util.Optional;
import java.util.UUID;

public interface CandidatService extends BaseService<CandidatDto, UUID>{
    Optional<CandidatDto> findById(UUID id);

    CandidatDto save(CandidatDto candidateDto);

//    CandidatDto update(CandidatDto candidateDto);

    void deleteById(UUID id);
}
