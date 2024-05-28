package ma.marjane.digitalisation_processus_recrutement.db1.service.impl;

import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.db1.dto.UtilisateurDto;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.UtilisateurRepository;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Utilisateur;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl.UtilisateurMapperImpl;
import ma.marjane.digitalisation_processus_recrutement.db1.service.UtilisateurService;
import ma.marjane.digitalisation_processus_recrutement.db2.entity.User;
import ma.marjane.digitalisation_processus_recrutement.db2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImp implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapperImpl utilisateurMapper;
    private final UserRepository userRepository;

    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream().map(utilisateurMapper::convertToDto).toList();
    }

    public Optional<UtilisateurDto> findById(String id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);
        return optionalUtilisateur.map(utilisateurMapper::convertToDto);
    }

    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        utilisateurRepository.save(utilisateurMapper.convertToEntity(utilisateurDto));
        return utilisateurDto;
    }

//    public UtilisateurDto update(UtilisateurDto utilisateurDto) {
//        Optional<UtilisateurDto> optionalUtilisateurDto = this.findById(utilisateurDto.getId());
//
//        if (optionalUtilisateurDto.isPresent()){
//            utilisateurRepository.save(utilisateurMapper.convertToEntity(utilisateurDto));
//            return utilisateurDto;
//        } else {
//            // Handle case when candidate with given id is not found
//            throw new RuntimeException("Utilisateur with id " + utilisateurDto.getId() + " not found");
//        }
//    }

    public void deleteById(String id) {
        utilisateurRepository.deleteById(id);
    }


    public void saveAllUsers() {
        this.saveAll(userRepository.findAllUsers());
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
