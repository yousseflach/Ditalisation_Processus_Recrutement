package ma.marjane.digitalisation_processus_recrutement.db1.service.impl;


import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.db1.dto.UtilisateurDto;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Utilisateur;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl.UtilisateurMapperImpl;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.UtilisateurRepository;
import ma.marjane.digitalisation_processus_recrutement.db2.entity.User;
import ma.marjane.digitalisation_processus_recrutement.db2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UtilisateurServiceImp {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapperImpl utilisateurMapper;
    private final UserRepository userRepository;

    public UtilisateurDto getUserDTOByEmail(String mail) {
        Utilisateur utilisateur = utilisateurRepository.findByMail(mail);
        return utilisateurMapper.convertToDto(utilisateur);
    }
    public UtilisateurDto getDemandeur(String matricule)  {
        Utilisateur utilisateur = utilisateurRepository.findByMatricule(matricule);
        return utilisateurMapper.convertToDto(utilisateur);
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
