package ma.marjane.digitalisation_processus_recrutement.db1.controller;


import ma.marjane.digitalisation_processus_recrutement.db1.dto.UtilisateurDto;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.ListRH;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Utilisateur;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl.UtilisateurMapperImpl;
import ma.marjane.digitalisation_processus_recrutement.db1.record.Listesousdirection;
import ma.marjane.digitalisation_processus_recrutement.db1.record.NomPrenomMatricule;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.ListRHRepository;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.UtilisateurRepository;
import ma.marjane.digitalisation_processus_recrutement.db1.service.impl.UtilisateurServiceImp;
//import ma.marjane.digitalisation_processus_recrutement.db2.entity.User;
//import ma.marjane.digitalisation_processus_recrutement.db2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("login")
public class UtilisateurController {

    @Autowired
    private UtilisateurServiceImp utilisateurService;

    @Autowired
    private ListRHRepository ListRHRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private UtilisateurMapperImpl utilisateurMapper;
//    @Autowired
//    private UserRepository userRepository;

    @PostMapping("/authenticate")
    public UtilisateurDto authenticate(@RequestParam String mail) throws Exception {
        UtilisateurDto utilisateurDTO = utilisateurService.getUserDTOByEmail(mail);
        // Vous pouvez ajouter ici des vérifications ou des traitements supplémentaires si nécessaire
        ListRH listRH = ListRHRepository.findByMatricule(utilisateurDTO.getMatricule());
        if (utilisateurDTO == null) {
            throw new RuntimeException("Utilisateur non trouvé");
        }else if (listRH != null){
            utilisateurDTO.setRole("RH");
        }
        else
            utilisateurDTO.setRole("Manager");
        return utilisateurDTO;
    }

//    @PostMapping("/demandeur/{matricule}")
//    public UtilisateurDto getDemandeur(@PathVariable String matricule){
//        return utilisateurService.getDemandeur(matricule);
//
//    }
    @GetMapping("/Demandeur/{matricule}")
    public UtilisateurDto getdemandeur(@PathVariable String matricule) {
        Utilisateur utilisateur =utilisateurRepository.findByMatricule(matricule);
        return utilisateurMapper.convertToDto(utilisateur);
    }

    @GetMapping("/demandeur/listeUo")
    public List<Listesousdirection> getUO(@RequestParam String matricule) {
        try {
            Utilisateur utilisateur = utilisateurRepository.findByMatricule(matricule);

            if (utilisateur == null) {
                // Si aucun utilisateur n'est trouvé avec ce matricule, retourner une liste vide
                return Collections.emptyList();
            }

            // Récupérer la liste des unités organisationnelles du manager 1 de l'utilisateur
            return utilisateurRepository.findAllUO(utilisateur.getMatricule());

        } catch (Exception e) {
            e.printStackTrace(); // Gérer l'exception ou journaliser le problème
            return Collections.emptyList(); // Retourner une liste vide en cas d'exception
        }
    }

    //get all emplois by code_uo if code_uo is null return exception or listemplois is empty return message "no emploi"
    @GetMapping("/demandeur/listeEmploi")
    public List<String> getEmploi(@RequestParam String code_uo) {
        try {
            if (code_uo == null) {
                throw new Exception("Code UO est null");
            }
            List<String> listEmploi = utilisateurRepository.findAllEmploiByCodeUO(code_uo);
            if (listEmploi.isEmpty()) {
                throw new Exception("No emploi");
            }
            return listEmploi;
        } catch (Exception e) {
            e.printStackTrace(); // Gérer l'exception ou journaliser le problème
            return Collections.singletonList(e.getMessage()); // Retourner un message d'erreur en cas d'exception
        }
    }

    //get all emplois by etablissement or direction si direction or etablissement is null return listemplois empty
    @GetMapping("/demandeur/listeEmploiByDirectionOrEtablissement")
    public List<String> getEmploiByDirectionOrEtablissement(@RequestParam String direction) {
        try {
            if (direction == null) {
                return Collections.emptyList();
            }
            List<String> listEmploi = utilisateurRepository.findAllEmploiByDirectionOrEtablissement(direction);
            if (listEmploi.isEmpty()) {
                return Collections.emptyList();
            }
            return listEmploi;
        } catch (Exception e) {
            return Collections.singletonList(e.getMessage());
        }
    }


    @GetMapping("/demandeur/liste_nom_prenom_matricule")
    public List<NomPrenomMatricule> getNomPrenomMatricule(@RequestParam String matricule) {
//        Utilisateur utilisateur =utilisateurRepository.findByDirection(matricule);
//        Utilisateur utilisateur2 =utilisateurRepository.findByMatricule(utilisateur.getManager1());
        utilisateurRepository.findAllNomPrenomMatricule(matricule).stream().map(npm -> {
                    NomPrenomMatricule nomPrenomMatricule = new NomPrenomMatricule(npm.nom(), npm.prenom(), npm.matricule());
                    System.out.println("Processing: " + nomPrenomMatricule.nom() + " " + nomPrenomMatricule.prenom() + " " + nomPrenomMatricule.matricule());
                    return nomPrenomMatricule;
                })
                .collect(Collectors.toList());
        return utilisateurRepository.findAllNomPrenomMatricule(matricule);
    }


//    @GetMapping("/save-all-users")
//    public ResponseEntity<String> getAllUsers() {
//        this.saveAll(userRepository.findAllUsers());
//        return ResponseEntity.status(HttpStatus.OK).body("All users have been saved successfully.");
//    }

//    public void saveAll(List<User> users) {
//        List<Utilisateur> utilisateurs = users.stream()
//                .map(this::mapUserToUtilisateur)
//                .collect(Collectors.toList());
//        utilisateurRepository.saveAll(utilisateurs);
//    }

//    private Utilisateur mapUserToUtilisateur(User user) {
//        Utilisateur utilisateur = new Utilisateur();
//        utilisateur.setMatricule(user.getMatricule().trim());
//        utilisateur.setNom(user.getNom());
//        utilisateur.setPrenom(user.getPrenom());
//        utilisateur.setSociete(user.getSociete());
//        utilisateur.setCode_Etablissement(user.getCodeEtablissement());
//        utilisateur.setEtablissement(user.getEtablissement());
//        utilisateur.setCode_Emploi(user.getCodeEmploi());
//        utilisateur.setEmploi(user.getEmploi());
//        utilisateur.setCode_uo(user.getCodeUo());
//        utilisateur.setUo(user.getUo());
//        utilisateur.setMail(user.getMail());
//        utilisateur.setDirection(user.getDirection());
//        utilisateur.setManager1(user.getManager1());
//        utilisateur.setManager2(user.getManager2());
//        utilisateur.setAffectation(user.getAffectation());
//        utilisateur.setComex(user.getComex());
//        return utilisateur;
//    }
}

