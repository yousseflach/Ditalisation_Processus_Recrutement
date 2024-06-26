package ma.marjane.digitalisation_processus_recrutement.db1.service.impl;

import ma.marjane.digitalisation_processus_recrutement.db1.dto.HierarchieDTO;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.*;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl.HierarchieMapper;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HierarchieService {
    @Autowired
    private HierarchieRepository hierarchieRepository;

    @Autowired
    private CollaborateurRepository collaborateurRepository;

    @Autowired
    private HierarchieMapper hierarchieMapper;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ListRHRepository listRHRepository;

    @Autowired
    private TacheRepository tacheRepository;


    public List<HierarchieDTO> getAllHierarchies() {
        List<Hierarchie> hierarchies = hierarchieRepository.findAll();
        return hierarchies.stream()
                .map(hierarchieMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public List<HierarchieDTO> getHierarchieById(UUID demandeId) {
        List<Hierarchie> hierarchies = hierarchieRepository.findByDemandeId(demandeId);
        List<HierarchieDTO> hierarchieDTOS = hierarchies.stream()
                .map(hierarchieMapper::convertToDto)
                .toList();
        return hierarchieDTOS;
    }

    //
//valider demande
    public boolean validerdemande(UUID demandeId, String matricule) {
        // Récupérer la demande
        Optional<Collaborateur> demandeOpt = collaborateurRepository.findById(demandeId);
        if (!demandeOpt.isPresent()) {
            // Gérer le cas où la demande n'est pas trouvée
            System.err.println("Demande non trouvée pour l'ID : " + demandeId);
            return false;
        }
        Demande demande = demandeOpt.get();

        // Récupérer l'utilisateur par matricule
        Utilisateur utilisateur = utilisateurRepository.findByMatricule(demande.getMatricule());
        if (utilisateur == null) {
            // Gérer le cas où l'utilisateur n'est pas trouvé
            System.err.println("Utilisateur non trouvé pour le matricule : " + demande.getMatricule());
            return false;
        }

        // Créer un ensemble de hiérarchies qui maintient l'ordre d'insertion et supprime les doublons
        Set<String> hierarchies = new LinkedHashSet<>();
        if (utilisateur.getManager1() != null) hierarchies.add(utilisateur.getManager1().trim());
        if (utilisateur.getManager2() != null) hierarchies.add(utilisateur.getManager2().trim());
        if (utilisateur.getComex() != null) hierarchies.add(utilisateur.getComex().trim());

        // Afficher les hiérarchies pour le débogage
        System.out.println(hierarchies);
        // Convertir le LinkedHashSet en ArrayList pour accéder par index
        List<String> hierarchiesList = new ArrayList<>(hierarchies);
        // Vérifier si le matricule existe dans hierarchiesList
        if (hierarchiesList.contains(matricule.trim())) {
            System.out.println("Le matricule " + matricule.trim() + " existe dans les hiérarchies.");
            // verifier si le matricule n est pas  le dernier de la liste
            if (hierarchiesList.indexOf(matricule.trim()) != hierarchiesList.size() - 1) {
                // valider la demande
                Hierarchie hierarchie;
                hierarchie = hierarchieRepository.findByDemandeIdAndMatriculeAndStatut(demandeId, matricule,"En cours");
                hierarchie.setStatut("Valider");
                hierarchieRepository.save(hierarchie);
                // Récupérer l'index du matricule dans la liste
                int index = hierarchiesList.indexOf(matricule.trim());
                // Récupérer le matricule suivant
                String nextMatricule = hierarchiesList.get(index + 1);
                // Récupérer le nom et le prénom de l'utilisateur suivant
                Utilisateur nextUtilisateur = utilisateurRepository.findByMatricule(nextMatricule);
                // Créer une nouvelle hiérarchie
                hierarchie= new Hierarchie();
                hierarchie.setDemande(demande); // 1
                hierarchie.setMatricule(nextMatricule);
                hierarchie.setNom(nextUtilisateur.getNom());
                hierarchie.setPrenom(nextUtilisateur.getPrenom());
                hierarchie.setStatut("En cours");
                hierarchie.setDatedecreation(new Date());  // Assuming setDatedecreation accepts a Date object
                hierarchieRepository.save(hierarchie);
            }else {
                // valider la demande
                Hierarchie hierarchie;
                hierarchie = hierarchieRepository.findByDemandeIdAndMatriculeAndStatut(demandeId, matricule,"En cours");
                hierarchie.setStatut("Valider");
                hierarchie.setDatedecreation(new Date());  // Assuming setDatedecreation accepts a Date object
                hierarchie.setDemande(demande);
                hierarchieRepository.save(hierarchie);
                //save hierarchie
                hierarchie.setDatedecreation(new Date());
                hierarchie.setMatricule("012279F");
                hierarchie.setNom("ABARAR HABIB");
                hierarchie.setPrenom("LAILA");
                hierarchie.setStatut("En cours");
                hierarchieRepository.save(hierarchie);
                System.out.println("La demande est validée."+hierarchie.getNom());
                // Ajouter une tâche
//            Tache tache = Tache.builder()
//                    .demande(demande)
//                    .etape("Ajouter Cvs")
//                    .dateDeDebut(LocalDateTime.now())
//                    .build();
//            tacheRepository.save(tache);
            }
        } else {
            // Ajouter une tâche
            Tache tache = Tache.builder()
                    .demande(demande)
                    .etape("Ajouter Cvs")
                    .dateDeDebut(LocalDateTime.now())
                    .build();
            tacheRepository.save(tache);
            System.out.println("Le matricule " + matricule.trim() + " n'existe pas dans les hiérarchies.");
            return false;
        }



        // Vous pouvez ajouter ici des vérifications supplémentaires si nécessaire
        // Par exemple, vérifier si le matricule de l'utilisateur est dans l'ensemble de hiérarchies

        return true;
    }

    public boolean refuserdemande(UUID demandeId, String matricule, String commentaire) {
        List<Hierarchie> hierarchies = hierarchieRepository.findByDemandeId(demandeId);
        Collaborateur collaborateur = collaborateurRepository.findById(demandeId).get();
        Utilisateur utilisateur = utilisateurRepository.findByMatricule(collaborateur.getMatricule());
        for (Hierarchie hierarchie : hierarchies) {
            if (hierarchie.getStatut().equals("En cours") && hierarchie.getMatricule().equals(matricule)) {
                hierarchie.setStatut("Refuser");
                Hierarchie hierarchie1 = new Hierarchie();
                hierarchie1.setDemande(collaborateur);
                hierarchie1.setStatut("En cours");
                hierarchie1.setMatricule(utilisateur.getMatricule());
                hierarchie1.setCommentaire(commentaire);
                hierarchie1.setNom(utilisateur.getNom());
                hierarchie1.setPrenom(utilisateur.getPrenom());
                hierarchie1.setDatedecreation(new Date());  // Assuming setDatedecreation accepts a Date object
                hierarchieRepository.save(hierarchie1);
                hierarchieRepository.save(hierarchie);
                return true;
            }else if (hierarchie.getStatut().equals("En cours") && listRHRepository.findByMatricule(matricule) != null){
                ListRH listRH = listRHRepository.findByMatricule(matricule);
                hierarchie.setDemande(collaborateur);
                hierarchie.setMatricule(listRH.getMatricule());
                hierarchie.setNom(listRH.getNom());
                hierarchie.setPrenom(listRH.getPrenom());
                hierarchie.setStatut("Refuser");
                hierarchie.setCommentaire(commentaire);
                hierarchie.setDatedecreation(new Date());  // Assuming setDatedecreation accepts a Date object
                ///////////////////////////////////////
                Hierarchie hierarchie1 = new Hierarchie();
                hierarchie1.setDemande(collaborateur);
                hierarchie1.setStatut("En cours");
                hierarchie1.setMatricule(utilisateur.getMatricule());
                hierarchie1.setCommentaire(commentaire);
                hierarchie1.setNom(utilisateur.getNom());
                hierarchie1.setPrenom(utilisateur.getPrenom());
                hierarchie1.setDatedecreation(new Date());  // Assuming setDatedecreation accepts a Date object
                hierarchieRepository.save(hierarchie1);
                hierarchieRepository.save(hierarchie);
                return true;
            }
        }
        return false;
    }


//    public HierarchieDTO createHierarchie(HierarchieDTO hierarchieDTO) {
//        Hierarchie hierarchie = hierarchieMapper.convertToEntity(hierarchieDTO);
//        hierarchie = hierarchieRepository.save(hierarchie);
//        return hierarchieMapper.convertToDto(hierarchie);
//    }

    //    public HierarchieDTO updateHierarchie(UUID id, HierarchieDTO hierarchieDTO) {
//        Optional<Hierarchie> existingHierarchie = hierarchieRepository.findById(id);
//        if (existingHierarchie.isPresent()) {
//            Hierarchie hierarchie = existingHierarchie.get();
//            hierarchie.setMatricule(hierarchieDTO.getMatricule());
//            hierarchie.setNom(hierarchieDTO.getNom());
//            hierarchie.setPrenom(hierarchieDTO.getPrenom());
//            hierarchie.setDatedecreation(hierarchieDTO.getDatedecreation());
//            hierarchie.setCommentaire(hierarchieDTO.getCommentaire());
//            hierarchie = hierarchieRepository.save(hierarchie);
//            return hierarchieMapper.convertToDto(hierarchie);
//        } else {
//            return null;
//        }
//    }
    public List<HierarchieDTO> getHierarchieByDemandeId(UUID demandeId) {
        List<Hierarchie> hierarchies = hierarchieRepository.findByDemandeId(demandeId);
        return hierarchies.stream()
                .map(hierarchieMapper::convertToDto)
                .sorted(Comparator.comparing(HierarchieDTO::getDatedecreation))
                .collect(Collectors.toList());
    }



    public List<Collaborateur> getdemandes(String matricule) {
        List<Hierarchie> hierarchies = hierarchieRepository.findByMatricule(matricule);
        List<Collaborateur> collaborateurs = new ArrayList<>();
        ListRH listRH = listRHRepository.findByMatricule(matricule);
        if (listRH != null){
//            hierarchies = hierarchieRepository.findByMatricule(matricule);
            hierarchies.forEach(hierarchie -> {
                Optional<Collaborateur> collaborateur = collaborateurRepository.findById(hierarchie.getDemande().getId());
                System.out.println("1 hierarchie demande :"+hierarchie.getDemande().getId());
                if (collaborateur.isPresent() && hierarchie.getStatut().equals("En cours")){
                    collaborateurs.add(collaborateur.get());
                }
            });
            hierarchies = hierarchieRepository.findByMatriculeAndStatut(matricule,"Valider");
            hierarchies.forEach(hierarchie -> {
                Optional<Collaborateur> collaborateur = collaborateurRepository.findById(hierarchie.getDemande().getId());
                collaborateurs.add(collaborateur.get());
            });

            return collaborateurs;
        }else{
            hierarchies.forEach(hierarchie -> {
                Optional<Collaborateur> collaborateur = collaborateurRepository.findById(hierarchie.getDemande().getId());
                System.out.println("2 hierarchie demande :"+hierarchie.getNom());
                if (collaborateur.isPresent() && hierarchie.getStatut().equals("En cours")){
                    collaborateurs.add(collaborateur.get());
                }
            });}
//        collaborateurs.stream().forEach(collaborateur -> {
//            System.out.println("3 hierarchie demande :" + collaborateur.getId());
//        });
        return collaborateurs;
    }

    public List<HierarchieDTO> getAllDemandes() {
        List<Hierarchie> hierarchies = hierarchieRepository.findAll();
        return hierarchies.stream()
                .map(hierarchieMapper::convertToDto)
                .sorted(Comparator.comparing(HierarchieDTO::getDatedecreation))
                .collect(Collectors.toList());
    }

    public boolean demandestvalider(String matricule, UUID demandeId) {
        List<Hierarchie> hierarchies = hierarchieRepository.findByDemandeIdAndMatricule(demandeId, matricule);
        for (Hierarchie hierarchie : hierarchies) {
            if (hierarchie.getMatricule().equals(matricule) && hierarchie.getStatut().equals("valider")) {
                return true;
            }
        }
        return false;

    }
}
