package ma.marjane.digitalisation_processus_recrutement.db1.service.impl;

import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.db1.dto.InterviewDto;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Interview;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl.InterviewMapperImpl;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.InterviewRepository;
import ma.marjane.digitalisation_processus_recrutement.db1.service.InterviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InterviewServiceImp implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final InterviewMapperImpl interviewMapper;

    public List<InterviewDto> findAll() {
        return interviewRepository.findAll().stream().map(interviewMapper::convertToDto).toList();
    }

    public Optional<InterviewDto> findById(UUID id){
        Optional<Interview> interviewOptional = interviewRepository.findById(id);
        return interviewOptional.map(interviewMapper::convertToDto);
    }

    public InterviewDto save(InterviewDto interviewDto) {
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
