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
    private  DemandeRepository demandeRepository;

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
        List<ListRH> listRHList = new ArrayList<>();
        // Récupérer la demande
        Optional<Demande> demandeOpt = demandeRepository.findById(demandeId);
        if (demandeOpt.isEmpty()) {
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
        hierarchies.add(demande.getMatricule().trim());
        if (utilisateur.getManager1() != null) hierarchies.add(utilisateur.getManager1().trim());
        if (utilisateur.getManager2() != null) hierarchies.add(utilisateur.getManager2().trim());
        if (utilisateur.getComex() != null) hierarchies.add(utilisateur.getComex().trim());

        if (demande.getSociete().equals("ACM") || demande.getSociete().equals("TXP") || demande.getSociete().equals("PRX")){
             listRHList = listRHRepository.findBySocieteAndAffectation("ACM", utilisateur.getAffectation());


        }else if (demande.getSociete().equals("DGB") ){
            // ajouter equipe RH dans l' hierarchie
             listRHList = listRHRepository.findBySocieteAndAffectation("DGB", utilisateur.getAffectation());
        }else{
            listRHList = listRHRepository.findBySocieteAndAffectation("COF", utilisateur.getAffectation());
        }
        //verifier si la liste est vide
        if (listRHList.isEmpty()) {
            System.err.println("Liste RH vide pour la société : " + demande.getSociete() + " et l'affectation : " + utilisateur.getAffectation());
            return false;
        }
        //trier listRHList par niveau
        listRHList.sort(Comparator.comparing(ListRH::getNiveau));
        // Ajouter les matricules des RH dans l'ensemble de hiérarchies
        listRHList.forEach(listRH -> {
            hierarchies.add(listRH.getMatricule());
        });
        //delete  matricule equals to 012272F  Azami Ayoub
        hierarchies.remove("012247F");
        //if demande type equal "Recrutement" remove niveau3 taha
        if (demande.getType().equals("Recrutement") && demande.getSociete()!="COF"){
            //converti demande to collaborateur
            Collaborateur collaborateur = collaborateurRepository.findById(demandeId).get();
            if (!collaborateur.getCategorie().equals("CSU")){
                hierarchies.remove("012272F");
            }else
                //ajputer ce matricule 012272F a la fin de la liste
                hierarchies.add("012272F");
        }
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
                hierarchie.setDemande(demande);
                hierarchieRepository.save(hierarchie);
                if (demande.getType().equals("Recrutement") && !demande.getSociete().equals("COF")){
                    Utilisateur utilisateur1=null;
                    //converti demande to collaborateur
                    Collaborateur collaborateur = collaborateurRepository.findById(demandeId).get();
                    if (!collaborateur.getCategorie().equals("CSU")){
                         utilisateur1 = utilisateurRepository.findByMatricule(hierarchiesList.get(hierarchiesList.size() - 2));
                        //save hierarchie
                        hierarchie = new Hierarchie();
                        hierarchie.setDemande(demande);
                        hierarchie.setStatut("En cours");
                        hierarchie.setMatricule(hierarchiesList.get(hierarchiesList.size() - 2));
                        hierarchie.setNom(utilisateur1.getNom());
                        hierarchie.setPrenom(utilisateur1.getPrenom());
                        hierarchie.setDatedecreation(new Date());  // Assuming setDatedecreation accepts a Date object
                        hierarchieRepository.save(hierarchie);

                    }else{
                         utilisateur1 = utilisateurRepository.findByMatricule(hierarchiesList.get(hierarchiesList.size() - 3));
                    //save hierarchie
                    hierarchie = new Hierarchie();
                    hierarchie.setDemande(demande);
                    hierarchie.setStatut("En cours");
                    hierarchie.setMatricule(hierarchiesList.get(hierarchiesList.size() - 3));
                    hierarchie.setNom(utilisateur1.getNom());
                    hierarchie.setPrenom(utilisateur1.getPrenom());
                    hierarchie.setDatedecreation(new Date());  // Assuming setDatedecreation accepts a Date object
                    hierarchieRepository.save(hierarchie);}


                }else{
                // Ajouter une tâche
                   Utilisateur utilisateur1 = utilisateurRepository.findByMatricule(hierarchiesList.get(hierarchiesList.size() - 3));
                    //save hierarchie
                    hierarchie = new Hierarchie();
                    hierarchie.setDemande(demande);
                    hierarchie.setStatut("En cours");
                    hierarchie.setMatricule(hierarchiesList.get(hierarchiesList.size() - 3));
                    hierarchie.setNom(utilisateur1.getNom());
                    hierarchie.setPrenom(utilisateur1.getPrenom());
                    hierarchie.setDatedecreation(new Date());  // Assuming setDatedecreation accepts a Date object
                    hierarchieRepository.save(hierarchie);
            Tache tache = Tache.builder()
                    .demande(demande)
                    .etape("Ajouter Cvs")
                    .dateDeDebut(LocalDateTime.now())
                    .build();
            tacheRepository.save(tache);
            }
            }
            return true;
        } else {
            System.err.println("Le matricule " + matricule.trim() + " n'existe pas dans les hiérarchies.");
            return false;
        }



        // Vous pouvez ajouter ici des vérifications supplémentaires si nécessaire
        // Par exemple, vérifier si le matricule de l'utilisateur est dans l'ensemble de hiérarchies

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



    public List<Demande> getdemandes(String matricule) {

        return hierarchieRepository.findByMatriculeAndStatut(matricule,"En cours").stream()
                .map(Hierarchie::getDemande)
                .collect(Collectors.toList());
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
