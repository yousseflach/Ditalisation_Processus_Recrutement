package ma.marjane.digitalisation_processus_recrutement.service.impl;

import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.dto.EntretienDto;
import ma.marjane.digitalisation_processus_recrutement.entity.Entretien;
import ma.marjane.digitalisation_processus_recrutement.mapper.impl.EntretienMapperImpl;
import ma.marjane.digitalisation_processus_recrutement.repository.EntretienRepository;
import ma.marjane.digitalisation_processus_recrutement.service.EntretienService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EntretienServiceImp implements EntretienService {

    private final EntretienRepository interviewRepository;
    private final EntretienMapperImpl interviewMapper;

    public List<EntretienDto> findAll() {
        return interviewRepository.findAll().stream().map(interviewMapper::convertToDto).toList();
    }

    public Optional<EntretienDto> findById(UUID id){
        Optional<Entretien> interviewOptional = interviewRepository.findById(id);
        return interviewOptional.map(interviewMapper::convertToDto);
    }

    public EntretienDto save(EntretienDto interviewDto) {
        interviewRepository.save(interviewMapper.convertToEntity(interviewDto));
        return interviewDto;
    }

//    public InterviewDto update(InterviewDto interviewDto) {
//        Optional<InterviewDto> optionalInterviewDto = this.findById(interviewDto.getId());
//
//        if (optionalInterviewDto.isPresent()) {
//            interviewRepository.save(interviewMapper.convertToEntity(interviewDto));
//            return interviewDto;
//        } else {
//            // Handle case when candidate with given id is not found
//            throw new RuntimeException("Interview with id " + interviewDto.getId() + " not found");
//        }
//    }

    public void deleteById(UUID id) {
        interviewRepository.deleteById(id);
    }
}
