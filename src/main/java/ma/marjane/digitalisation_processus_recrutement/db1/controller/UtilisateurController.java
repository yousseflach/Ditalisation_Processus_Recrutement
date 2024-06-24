package ma.marjane.digitalisation_processus_recrutement.db1.controller;


import ma.marjane.digitalisation_processus_recrutement.db1.dto.UtilisateurDto;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.ListRH;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Utilisateur;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl.UtilisateurMapperImpl;
import ma.marjane.digitalisation_processus_recrutement.db1.record.NomPrenomMatricule;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.ListRHRepository;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.UtilisateurRepository;
import ma.marjane.digitalisation_processus_recrutement.db1.service.impl.UtilisateurServiceImp;
import ma.marjane.digitalisation_processus_recrutement.db2.entity.User;
import ma.marjane.digitalisation_processus_recrutement.db2.repository.UserRepository;
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
    @Autowired
    private UserRepository userRepository;

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
    public List<String> getUO(@RequestParam String matricule) {
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


    @GetMapping("/save-all-users")
    public ResponseEntity<String> getAllUsers() {
        this.saveAll(userRepository.findAllUsers());
        return ResponseEntity.status(HttpStatus.OK).body("All users have been saved successfully.");
    }

    public void saveAll(List<User> users) {
        List<Utilisateur> utilisateurs = users.stream()
                .map(this::mapUserToUtilisateur)
                .collect(Collectors.toList());
        utilisateurRepository.saveAll(utilisateurs);
    }

    private Utilisateur mapUserToUtilisateur(User user) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMatricule(user.getMatricule().trim());
        utilisateur.setNom(user.getNom());
        utilisateur.setPrenom(user.getPrenom());
        utilisateur.setSociete(user.getSociete());
        utilisateur.setCode_Etablissement(user.getCodeEtablissement());
        utilisateur.setEtablissement(user.getEtablissement());
        utilisateur.setCode_Emploi(user.getCodeEmploi());
        utilisateur.setEmploi(user.getEmploi());
        utilisateur.setCode_uo(user.getCodeUo());
        utilisateur.setUo(user.getUo());
        utilisateur.setMail(user.getMail());
        utilisateur.setDirection(user.getDirection());
        utilisateur.setManager1(user.getManager1());
        utilisateur.setManager2(user.getManager2());
        utilisateur.setAffectation(user.getAffectation());
        utilisateur.setComex(user.getComex());
        return utilisateur;
    }
}

