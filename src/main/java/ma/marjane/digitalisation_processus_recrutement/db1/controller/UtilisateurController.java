package ma.marjane.digitalisation_processus_recrutement.db1.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.db1.dto.UtilisateurDto;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Utilisateur;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.UtilisateurRepository;
import ma.marjane.digitalisation_processus_recrutement.db1.service.impl.UtilisateurServiceImp;
import ma.marjane.digitalisation_processus_recrutement.db2.entity.User;
import ma.marjane.digitalisation_processus_recrutement.db2.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private final UtilisateurServiceImp utilisateurService;
    private final UserRepository userRepository;
    private final UtilisateurRepository utilisateurRepository;

    @GetMapping
    public ResponseEntity<List<UtilisateurDto>> getAllUtilisateurs() {
        List<UtilisateurDto> utilisateurs = utilisateurService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(utilisateurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable String id) {
        return utilisateurService.findById(id)
                .map(utilisateurDto -> ResponseEntity.status(HttpStatus.OK).body(utilisateurDto))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UtilisateurDto> createUtilisateur(@Valid @RequestBody UtilisateurDto utilisateurDto) {
        UtilisateurDto createdUtilisateurDto = utilisateurService.save(utilisateurDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUtilisateurDto);
    }

//    @PutMapping
//    public ResponseEntity<UtilisateurDto> updateUtilisateur(@Valid @RequestBody UtilisateurDto utilisateurDto) {
//        UtilisateurDto updatedUtilisateurDto = utilisateurService.update(utilisateurDto);
//        return ResponseEntity.status(HttpStatus.OK).body(updatedUtilisateurDto);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable String id) {
        utilisateurService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
        utilisateur.setMatricule(user.getMatricule());
        utilisateur.setNom(user.getNom());
        utilisateur.setPrenom(user.getPrenom());
        utilisateur.setSociete(user.getSociete());
        utilisateur.setCodeEtablissement(user.getCodeEtablissement());
        utilisateur.setEtablissement(user.getEtablissement());
        utilisateur.setCodeEmploi(user.getCodeEmploi());
        utilisateur.setEmploi(user.getEmploi());
        utilisateur.setCodeUo(user.getCodeUo());
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
