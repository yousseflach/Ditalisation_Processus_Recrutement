package ma.marjane.digitalisation_processus_recrutement.service;

import ma.marjane.digitalisation_processus_recrutement.entity.Collaborateur;
import ma.marjane.digitalisation_processus_recrutement.repository.CollaborateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CollaborateurService {

    @Autowired
    private CollaborateurRepository repository;

    public List<Collaborateur> findAll() {
        return repository.findAll();
    }

    public Optional<Collaborateur> findById(UUID id) {
        return repository.findById(id);
    }

    public Collaborateur save(Collaborateur collaborator) {
        return repository.save(collaborator);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
