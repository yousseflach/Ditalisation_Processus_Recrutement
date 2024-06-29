package ma.marjane.digitalisation_processus_recrutement.db1.service.impl;

import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.db1.dto.EntretienDto;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Candidat;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Utilisateur;
import ma.marjane.digitalisation_processus_recrutement.db1.handler.exception.EntityNotFoundException;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl.EntretienMapperImpl;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.CandidatRepository;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.EntretienRepository;
import ma.marjane.digitalisation_processus_recrutement.db1.repository.UtilisateurRepository;
import ma.marjane.digitalisation_processus_recrutement.db1.service.EntretienService;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Entretien;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EntretienServiceImp implements EntretienService {

    private final EntretienRepository entretienRepository;
    private final EntretienMapperImpl entretienMapper;
    private final UtilisateurRepository utilisateurRepository;
    private final CandidatRepository candidatRepository;

    public List<EntretienDto> findAll() {
        return entretienRepository.findAll().stream().map(entretienMapper::convertToDto).toList();
    }

    public Optional<EntretienDto> findById(UUID id){
        Optional<Entretien> interviewOptional = entretienRepository.findById(id);
        return interviewOptional.map(entretienMapper::convertToDto);
    }

    public EntretienDto save(EntretienDto interviewDto) {
        entretienRepository.save(entretienMapper.convertToEntity(interviewDto));
        return interviewDto;
    }

    public EntretienDto add(EntretienDto interviewDto, String createur, String evaluateur, UUID candidatId) {

        // Vérifiez l'existence des utilisateurs (créateur et évaluateur)
        Utilisateur ucreateur = utilisateurRepository.findByMatricule(createur);
        if (ucreateur == null) {
            throw new EntityNotFoundException("Créateur with matricule " + createur + " not found");
        }

        Utilisateur uevaluateur = utilisateurRepository.findByMatricule(evaluateur);
        if (uevaluateur == null) {
            throw new EntityNotFoundException("Évaluateur with matricule " + evaluateur + " not found");
        }

        // Vérifiez l'existence du candidat
        Candidat candidat = candidatRepository.findById(candidatId).orElseThrow(() ->
                new EntityNotFoundException("Candidat with ID " + candidatId + " not found")
        );

        // Convertir le DTO en entité
        Entretien entretien = entretienMapper.convertToEntity(interviewDto);

        // Assigner les utilisateurs et le candidat à l'entretien
        entretien.setCreateur(ucreateur);
        entretien.setEvaluateur(uevaluateur);
        entretien.setCandidat(candidat);

        // Sauvegarder l'entretien
        entretienRepository.save(entretien);

        // Log (ou autre traitement si nécessaire)
        System.out.println("L'entretien dto: " + entretien.getId() + " le créateur: " + ucreateur.getNom() + " l'évaluateur: " + uevaluateur.getNom() + " le candidat: " + candidat.getNom());

        // Retourner le DTO mis à jour
        return interviewDto;
    }



//    public InterviewDto update(InterviewDto interviewDto) {
//        Optional<InterviewDto> optionalInterviewDto = this.findById(interviewDto.getId());
//
//        if (optionalInterviewDto.isPresent()) {
//            interviewRepository.save(interviewMapper.convertToEntity(interviewDto));
//            return interviewDto;
//        } else {
//            // Handle case when candidate with given id is not found
//            throw new RuntimeException("Interview with id " + interviewDto.getId() + " not found");
//        }
//    }

    public void deleteById(UUID id) {
        entretienRepository.deleteById(id);
    }
}
