package ma.marjane.digitalisation_processus_recrutement.db1.service.impl;

import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.*;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CandidateServiceImp {

    private final CandidatRepository candidateRepository;
    private final CollaborateurRepository collaborateurRepository;
    private final DemandeRepository demandeRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final HierarchieRepository hierarchieRepository;
    private final TacheRepository tacheRepository;
    private final ListRHRepository listRHRepository;
    private final String uploadDir = "uploads/";

    public Candidat saveCandidat(Candidat candidat, MultipartFile cv, UUID demandeId) throws IOException {
        //check folder exist
        Path path = Paths.get(uploadDir);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
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

    public List<Candidat> getAllCandidatesByDemandeId(UUID id) {
        return candidateRepository.findByDemandeId(id);
    }
    @Transactional
    public Boolean envoyerCv(UUID demandeid) {
        Demande demande = demandeRepository.findById(demandeid).orElseThrow(() -> new RuntimeException("Demande not found"));
        Utilisateur utilisateur = utilisateurRepository.findByMatricule(demande.getMatricule());
        List<Tache> tachefiltre=tacheRepository.findByDemandeIdAndEtape(demandeid,"Filtre Cvs");
        if(!tachefiltre.isEmpty()){
            return false;
        }
        assert utilisateur != null;
        Hierarchie hierarchie = hierarchieRepository.findByDemandeIdAndStatut(demandeid,"En cours");
        if (hierarchie == null && hierarchie.getMatricule().equals(utilisateur.getMatricule())) {
            return false;
        }
        hierarchie.setStatut("Valider");
        hierarchieRepository.save(hierarchie);
        hierarchie.setMatricule(utilisateur.getMatricule());
        hierarchie.setDatedecreation(new Date());
        hierarchie.setPrenom(utilisateur.getPrenom());
        hierarchie.setNom(utilisateur.getNom());
        hierarchie.setDemande(demande);
        hierarchie.setStatut("En cours");
        hierarchieRepository.save(hierarchie);
        Tache tache=Tache.builder()
                .dateDeDebut(LocalDateTime.now())
                .demande(demande)
                .etape("Filtre Cvs")
                .build();
        tacheRepository.save(tache);
        return true;
    }
@Transactional
    public Candidat saveUpdate(Candidat c) {
    return  candidateRepository.save(c);

}
@Transactional
    public void envoyerCvRh(UUID uuid) {
        //get by niveau
        Hierarchie hierarchie = hierarchieRepository.findByDemandeIdAndStatut(uuid, "En cours");
        hierarchie.setStatut("Valider");
        List<ListRH> listRHs = listRHRepository.findByNiveau("Niveau1");
        listRHs.forEach(listRH -> {
            List<Hierarchie> hierarchie1 = hierarchieRepository.findByDemandeIdAndMatricule(uuid, listRH.getMatricule());
            if (!hierarchie1.isEmpty())
            {
                Hierarchie hierarchie2=new Hierarchie();
                hierarchie2.setDemande(hierarchie.getDemande());
                hierarchie2.setMatricule(listRH.getMatricule());
                hierarchie2.setNom(listRH.getNom());
                hierarchie2.setPrenom(listRH.getPrenom());
                hierarchie2.setStatut("En cours");
                hierarchie2.setDatedecreation(new Date());
                hierarchieRepository.save(hierarchie2);
                Tache tache=Tache.builder()
                        .dateDeDebut(LocalDateTime.now())
                        .demande(hierarchie.getDemande())
                        .etape("planifier les entretiens")
                        .build();
                tacheRepository.save(tache);
                System.out.println("bien traité");
            }
        });
    }
}
