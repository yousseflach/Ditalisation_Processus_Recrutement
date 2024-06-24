package ma.marjane.digitalisation_processus_recrutement.db1.service.impl;

import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Candidat;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Collaborateur;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.CandidatRepository;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.CollaborateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CandidateServiceImp {

    private final CandidatRepository candidateRepository;
    private final CollaborateurRepository collaborateurRepository;
    private final String uploadDir = "uploads/";

    public Candidat saveCandidat(Candidat candidat, MultipartFile cv, UUID demandeId) throws IOException {
        Collaborateur collaborateur = collaborateurRepository.findById(demandeId).orElseThrow(() -> new RuntimeException("Demande not found"));


        String fileName = generateFileName(candidat.getNom(), candidat.getPrenom(), demandeId);

        // Save the file to the filesystem
        Path copyLocation = Paths.get(uploadDir + fileName);
        Files.copy(cv.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

        // Set the file path to the candidat entity
        candidat.setCvPath(copyLocation.toString());
        candidat.setDemande(collaborateur);
        return candidateRepository.save(candidat);
    }
    private String generateFileName(String nom, String prenom, UUID demandeId) {
        return nom + "_" + prenom + "_" + demandeId + ".pdf"; // Adjust the file extension as necessary
    }

    public Candidat getCandidatById(UUID id) {
        return candidateRepository.findById(id).orElseThrow(() -> new RuntimeException("Candidat not found"));
    }

    public List<Candidat> getAllCandidats() {
        return candidateRepository.findAll();
    }

    public Candidat updateCandidat(UUID id, Candidat updatedCandidat, MultipartFile cv) throws IOException {
        Candidat existingCandidat = candidateRepository.findById(id).orElseThrow(() -> new RuntimeException("Candidat not found"));

        if (cv != null && !cv.isEmpty()) {
            String fileName = generateFileName(updatedCandidat.getNom(), updatedCandidat.getPrenom(), existingCandidat.getDemande().getId());

            // Save the file to the filesystem
            Path copyLocation = Paths.get(uploadDir + fileName);
            Files.copy(cv.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

            // Set the file path to the candidat entity
            existingCandidat.setCvPath(copyLocation.toString());
        }

        existingCandidat.setNom(updatedCandidat.getNom());
        existingCandidat.setPrenom(updatedCandidat.getPrenom());
        existingCandidat.setEmail(updatedCandidat.getEmail());
        existingCandidat.setTelephone(updatedCandidat.getTelephone());
        // Add other fields as necessary

        return candidateRepository.save(existingCandidat);
    }


    public void deleteById(UUID id) {
        candidateRepository.deleteById(id);
    }
}
