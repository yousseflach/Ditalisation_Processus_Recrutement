package ma.marjane.digitalisation_processus_recrutement.db1.controller;

import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Candidat;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Demande;
import ma.marjane.digitalisation_processus_recrutement.db1.enumeration.StatutSelection;
import ma.marjane.digitalisation_processus_recrutement.db1.service.impl.CandidateServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
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

    // get all candidates by collaborator id
    @GetMapping("/{id}")
    public ResponseEntity<List<Candidat>> getAllCandidatesByDemandeId(@PathVariable UUID id) {
        List<Candidat> candidates = candidateServiceImp.getAllCandidatesByDemandeId(id);
        return ResponseEntity.ok(candidates);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable UUID id) {
        candidateServiceImp.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/envoyerCv/{demandeid}")
    public ResponseEntity<Boolean> envoyerCv(@PathVariable UUID demandeid) {
        return ResponseEntity.ok(candidateServiceImp.envoyerCv(demandeid));
    }

    @PostMapping("/envoyerCvrh/{demandeid}")
    public ResponseEntity<String> envoyerCvrh(
            @PathVariable("demandeid") String demandeid,
            @RequestParam List<String> candidats) {
        System.out.println("Received request to send CVs to HR for demande " + demandeid);
        System.out.println("Received " + candidats.size() + " candidates.");
        // Handle the request here. For example, save the candidats to the database.
        //make them selected
        for (String candidat : candidats) {
            Candidat c = candidateServiceImp.getCandidatById(UUID.fromString(candidat));
            c.setStatutSelection(StatutSelection.CHOISI);
            candidateServiceImp.saveUpdate(c);
        }
        System.out.println("Candidates saved successfully.");
        //make the others rejected
        List<Candidat> candidatsList = candidateServiceImp.getAllCandidatesByDemandeId(UUID.fromString(demandeid));
        for (Candidat candidat : candidatsList) {
            if (!candidats.contains(candidat.getId().toString())) {
                candidat.setStatutSelection(StatutSelection.REJETE);
                candidateServiceImp.saveUpdate(candidat);
            }
        }
        System.out.println("Candidates rejected successfully.");
        // Send the CVs to HR
        candidateServiceImp.envoyerCvRh(UUID.fromString(demandeid));
        // Return a response
        return ResponseEntity.ok("Candidates received successfully.");
    }
}
