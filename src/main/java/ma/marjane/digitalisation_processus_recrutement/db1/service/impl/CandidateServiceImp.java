package ma.marjane.digitalisation_processus_recrutement.db1.service.impl;

import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl.CandidateMapperImpl;
import ma.marjane.digitalisation_processus_recrutement.db1.dto.CandidatDto;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Candidat;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.CandidateRepository;
import ma.marjane.digitalisation_processus_recrutement.db1.service.CandidatService;
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
    public List<CandidatDto> findAll() {
        return candidateRepository.findAll().stream().map(candidateMapper::convertToDto).toList();
    }


    @Override
    public Optional<CandidatDto> findById(UUID id) {
        Optional<Candidat> candidatOptional = candidateRepository.findById(id);
        return candidatOptional.map(candidateMapper::convertToDto);
    }

    @Override
    public CandidatDto save(CandidatDto candidateDto) {
        candidateRepository.save(candidateMapper.convertToEntity(candidateDto));
        return candidateDto;
    }

//    @Override
//    public CandidatDto update(CandidatDto candidateDto){
//        //  find Candidate if exist
//        Optional<CandidatDto> optionalCandidateDto = this.findById(candidateDto.getId());
//        if (optionalCandidateDto.isPresent()) {
//            candidateRepository.save(candidateMapper.convertToEntity(candidateDto));
//            return candidateDto;
//        } else {
//            // Handle case when candidate with given id is not found
//            throw new RuntimeException("Candidate with id " + candidateDto.getId() + " not found");
//        }
//    }

    @Override
    public void deleteById(UUID id) {
        candidateRepository.deleteById(id);
    }
}
