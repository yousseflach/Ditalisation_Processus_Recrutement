package ma.marjane.digitalisation_processus_recrutement.db1.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.db1.dto.CollaborateurDto;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Collaborateur;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Hierarchie;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Tache;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Utilisateur;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl.CollaborateurMapperImpl;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.CollaborateurRepository;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.HierarchieRepository;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.TacheRepository;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.UtilisateurRepository;
import ma.marjane.digitalisation_processus_recrutement.db1.service.CollaborateurService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CollaborateurServiceImp implements CollaborateurService {


    private final CollaborateurRepository collaborateurRepository;
    private final CollaborateurMapperImpl collaborateurMapper;
    private final UtilisateurRepository utilisateurRepository;
    private final HierarchieRepository hierarchieRepository;
    private final TacheRepository tacheRepository;


    public List<CollaborateurDto> findAll() {
        return collaborateurRepository.findByAttributes(true).stream().map(collaborateurMapper::convertToDto).toList();
    }

    public List<CollaborateurDto> findByMatricule(String matricule) {
        List<Collaborateur> collaborateurOptional = collaborateurRepository.findByMatricule(matricule);
        return collaborateurOptional.stream().map(collaborateurMapper::convertToDto).toList();
    }

    @Override
    public CollaborateurDto save(CollaborateurDto collaborateurDto) {
        // Convert DTO to entity
        Collaborateur collaborateur = collaborateurMapper.convertToEntity(collaborateurDto);

        // Set creation date and initial status
        collaborateur.setDateDeCreation(LocalDateTime.now());
        collaborateur.setStatut("En cours");

        // Add initial task
        Tache tache = Tache.builder()
                .demande(collaborateur)
                .etape("Validation")
                .dateDeDebut(LocalDateTime.now())
                .build();
        collaborateur.getTaches().add(tache);

        // Fetch associated user
        Utilisateur utilisateur = utilisateurRepository.findByMatricule(collaborateurDto.getMatricule());
        if (utilisateur == null) {
            throw new RuntimeException("Utilisateur with matricule " + collaborateurDto.getMatricule() + " not found");
        }

        // Save collaborateur first to generate ID
        // collaborateur = collaborateurRepository.save(collaborateur);

        // Add hierarchies if required
//        if (collaborateur.isAttributes() && collaborateur.getHierarchies().isEmpty() && !utilisateur.getMatricule().equals(utilisateur.getComex())) {
//
//        } else if (utilisateur.getMatricule().equals(utilisateur.getComex())) {
//            Hierarchie hierarchieRh = Hierarchie.builder()
//                    .matricule("RH")
//                    .demande(collaborateur)
//                    .nom("RH")
//                    .prenom("")
//                    .datedecreation(new Date())
//                    .statut("En cours")
//                    .build();
//
//            collaborateur.getHierarchies().add(hierarchieRh);
//        }

        Utilisateur manager1 = utilisateurRepository.findByMatricule(utilisateur.getManager1());

        Hierarchie hierarchie1 = Hierarchie.builder()
                .matricule(utilisateur.getMatricule())
                .demande(collaborateur)
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .datedecreation(new Date())
                .statut("Valider")
                .build();

        Hierarchie hierarchie2 = Hierarchie.builder()
                .matricule(manager1.getMatricule())
                .demande(collaborateur)
                .nom(manager1.getNom())
                .prenom(manager1.getPrenom())
                .datedecreation(new Date())
                .statut("En cours")
                .build();

        collaborateur.getHierarchies().add(hierarchie1);
        collaborateur.getHierarchies().add(hierarchie2);

        // Save the updated collaborateur with hierarchies
        collaborateur = collaborateurRepository.save(collaborateur);

        // Return the DTO
        return collaborateurMapper.convertToDto(collaborateur);
    }

//    public CollaborateurDto save(CollaborateurDto collaborateurDto) {
//        Collaborateur collaborateur = collaborateurMapper.convertToEntity(collaborateurDto);
//        collaborateur.setDateDeCreation(LocalDateTime.now());
//        collaborateur.getTaches().add(Tache.builder()
//                .demandeId(collaborateur.getId())
//                .etape("Validation")
//                .dateDeDebut(LocalDateTime.now())
//                .build());
//        List<Collaborateur> collaborateurs = collaborateurRepository.findByMatricule(collaborateurDto.getMatricule());
//
//        Utilisateur utilisateur= utilisateurRepository.findByMatricule(collaborateurDto.getMatricule());
//        if (utilisateur==null){
//            throw new RuntimeException("Utilisateur with matricule " + collaborateurDto.getMatricule() + " not found");
//        }
//        if (!collaborateur.isAttributes())
//
//        for (Collaborateur collaborateur1 : collaborateurs) {
//            if (collaborateur.isAttributes())
//                break;
//            else if(!collaborateur1.isAttributes())
//                return collaborateurMapper.convertToDto(collaborateur1);
//        }
//        collaborateur.setStatut("En cours");
//        collaborateur=collaborateurRepository.save(collaborateur);
//        System.out.println("collaborateur.getId() = " + collaborateur.getId());
//        System.out.println("collaborateur.getId() = " + collaborateur.isAttributes());
//        if (collaborateur.isAttributes() && collaborateur.getHierarchies().isEmpty() && !utilisateur.getMatricule().equals(utilisateur.getComex())){
//        Utilisateur manager1= utilisateurRepository.findByMatricule(utilisateur.getManager1());
//        collaborateur.getHierarchies().add(Hierarchie.builder()
//                .demandeId(collaborateur.getId())
//                .matricule(utilisateur.getMatricule())
//                .nom(utilisateur.getNom())
//                .prenom(utilisateur.getPrenom())
//                .datedecreation(new Date())
//                .statut("valider")
//                .build());
//        collaborateur.getHierarchies().add(Hierarchie.builder()
//                .demandeId(collaborateur.getId())
//                .matricule(manager1.getMatricule())
//                .nom(manager1.getNom())
//                .prenom(manager1.getPrenom())
//                .datedecreation(new Date())
//                .statut("En cours")
//                .build());
//            collaborateur.setStatut("En cours");
//            collaborateurRepository.save(collaborateur);
//        }
//        else if (utilisateur.getMatricule().equals(utilisateur.getComex())){
//            collaborateur.getHierarchies().add(Hierarchie.builder()
//                    .demandeId(collaborateur.getId())
//                    .matricule("RH")
//                    .nom("RH")
//                    .prenom("")
//                    .datedecreation(new Date())
//                    .statut("En cours")
//                    .build());
//            collaborateur.setStatut("En cours");
//            collaborateurRepository.save(collaborateur);
//        }
//
//        return collaborateurDto;
//    }

//    public CollaborateurDto update(CollaborateurDto collaborateurDto) {
//        Optional<CollaborateurDto> optionalCollaborateurDto = this.findById(collaborateurDto.getId());
//        if (optionalCollaborateurDto.isPresent()) {
//            collaborateurRepository.save(collaborateurMapper.convertToEntity(collaborateurDto));
//            return collaborateurDto;
//        }else {
//            // Handle case when candidate with given id is not found
//            throw new RuntimeException("Demande Collaborateur with id " + collaborateurDto.getId() + " not found");
//        }
//    }

    public void deleteById(UUID demandeId) {
        Optional<Collaborateur> optionalDemande = collaborateurRepository.findById(demandeId);
        if (optionalDemande.isPresent()) {
            Collaborateur demande = optionalDemande.get();
            // Supprimer les hierarchies associées
            List<Hierarchie> hierarchies = hierarchieRepository.findByDemandeId(demandeId);
            hierarchieRepository.deleteAll(hierarchies);

            // Supprimer les taches associées
            List<Tache> taches = tacheRepository.findByDemandeId(demandeId);
            tacheRepository.deleteAll(taches);

            // Supprimer la demande
            collaborateurRepository.delete(demande);
        } else {
            throw new EntityNotFoundException("Demande not found with id: " + demandeId);
        }
    }
}
