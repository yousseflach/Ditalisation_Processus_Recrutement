package ma.marjane.digitalisation_processus_recrutement.db1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.db1.dto.EntretienDto;
import ma.marjane.digitalisation_processus_recrutement.db1.service.impl.EntretienServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/entretiens")
public class EntretienController {

    private final EntretienServiceImp interviewService;

    @GetMapping
    public ResponseEntity<List<EntretienDto>> getAllInterviews() {
        List<EntretienDto> interviews = interviewService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(interviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntretienDto> getInterviewById(@PathVariable UUID id) {
        return interviewService.findById(id)
                .map(interviewDto -> ResponseEntity.status(HttpStatus.OK).body(interviewDto))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<EntretienDto> createInterview(@Valid @RequestBody EntretienDto interviewDto) {
        EntretienDto createdInterviewDto = interviewService.save(interviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInterviewDto);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<InterviewDto> updateInterview(@Valid @RequestBody InterviewDto interviewDto) {
//        InterviewDto updatedInterviewDto = interviewService.update(interviewDto);
//        return ResponseEntity.status(HttpStatus.OK).body(updatedInterviewDto);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterview(@PathVariable UUID id) {
        interviewService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
