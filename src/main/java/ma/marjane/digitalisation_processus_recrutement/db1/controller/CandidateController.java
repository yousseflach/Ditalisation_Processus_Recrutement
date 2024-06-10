package ma.marjane.digitalisation_processus_recrutement.db1.controller;

import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Candidat;
import ma.marjane.digitalisation_processus_recrutement.db1.service.impl.CandidateServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("candidates")
public class CandidateController {

    private final CandidateServiceImp candidateServiceImp;


    @PostMapping("/upload")
    public ResponseEntity<Candidat> uploadCandidat(@RequestParam String nom,
                                                   @RequestParam String prenom,
                                                   @RequestParam String email,
                                                   @RequestParam String telephone,
                                                   @RequestParam MultipartFile cv,
                                                   @RequestParam UUID demandeId) throws IOException {
        Candidat candidat = new Candidat();
        candidat.setNom(nom);
        candidat.setPrenom(prenom);
        candidat.setEmail(email);
        candidat.setTelephone(telephone);
        Candidat savedCandidat = candidateServiceImp.saveCandidat(candidat, cv, demandeId);
        return ResponseEntity.ok(savedCandidat);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<CandidatDto> updateCandidate(@Valid @RequestBody CandidatDto candidateDto) {
//        CandidatDto updatedCandidateDto = candidateServiceImp.update(candidateDto);
//        return ResponseEntity.status(HttpStatus.OK).body(updatedCandidateDto);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable UUID id) {
        candidateServiceImp.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
