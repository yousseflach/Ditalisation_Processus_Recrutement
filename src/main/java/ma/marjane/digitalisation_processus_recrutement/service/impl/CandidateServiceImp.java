package ma.marjane.digitalisation_processus_recrutement.service.impl;

import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.dto.CandidateDto;
import ma.marjane.digitalisation_processus_recrutement.entity.Candidate;
import ma.marjane.digitalisation_processus_recrutement.mapper.impl.CandidateMapperImpl;
import ma.marjane.digitalisation_processus_recrutement.repository.CandidateRepository;
import ma.marjane.digitalisation_processus_recrutement.service.CandidatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CandidateServiceImp implements CandidatService {

    private final CandidateRepository candidateRepository;
    private final CandidateMapperImpl candidateMapper;


    @Override
    public List<CandidateDto> findAll() {
        return candidateRepository.findAll().stream().map(candidateMapper::convertToDto).toList();
    }


    @Override
    public Optional<CandidateDto> findById(UUID id) {
        Optional<Candidate> candidatOptional = candidateRepository.findById(id);
        return candidatOptional.map(candidateMapper::convertToDto);
    }

    @Override
    public CandidateDto save(CandidateDto candidateDto) {
        candidateRepository.save(candidateMapper.convertToEntity(candidateDto));
        return candidateDto;
    }

    @Override
    public CandidateDto update(CandidateDto candidateDto){
        //  find Candidate if exist
        Optional<CandidateDto> optionalCandidateDto = this.findById(candidateDto.getId());
        if (optionalCandidateDto.isPresent()) {
            candidateRepository.save(candidateMapper.convertToEntity(candidateDto));
            return candidateDto;
        } else {
            // Handle case when candidate with given id is not found
            throw new RuntimeException("Candidate with id " + candidateDto.getId() + " not found");
        }
    }

    @Override
    public void deleteById(UUID id) {
        candidateRepository.deleteById(id);
    }
}
