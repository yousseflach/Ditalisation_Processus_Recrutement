package ma.marjane.digitalisation_processus_recrutement.db1.service.impl;

import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.db1.dto.UtilisateurDto;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.UtilisateurRepository;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Utilisateur;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl.UtilisateurMapperImpl;
import ma.marjane.digitalisation_processus_recrutement.db1.service.UtilisateurService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImp implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapperImpl utilisateurMapper;

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
}