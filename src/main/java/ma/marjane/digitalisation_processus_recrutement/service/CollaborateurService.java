package ma.marjane.digitalisation_processus_recrutement.service;

import lombok.AllArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.dto.CollaborateurDto;
import ma.marjane.digitalisation_processus_recrutement.entity.Collaborateur;
import ma.marjane.digitalisation_processus_recrutement.mapper.impl.CollaborateurMapperImpl;
import ma.marjane.digitalisation_processus_recrutement.repository.CollaborateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CollaborateurService {


    private final CollaborateurRepository repository;
    private final CollaborateurMapperImpl collaborateurMapper;

    public List<Collaborateur> findAll() {
        return repository.findAll();
    }

    public Optional<Collaborateur> findById(UUID id) {
        return repository.findById(id);
    }

//    public Collaborateur save(CollaborateurDto collaborateurDto) {
//        return repository.save();
//    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
