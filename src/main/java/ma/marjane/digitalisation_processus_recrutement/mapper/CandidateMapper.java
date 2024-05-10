package ma.marjane.digitalisation_processus_recrutement.mapper;

import ma.marjane.digitalisation_processus_recrutement.dto.CandidateDto;
import ma.marjane.digitalisation_processus_recrutement.entity.Candidate;

public interface CandidateMapper {

    Candidate convertToEntity(CandidateDto candidateDto);
    CandidateDto convertToDto(Candidate candidateEntity);

}
