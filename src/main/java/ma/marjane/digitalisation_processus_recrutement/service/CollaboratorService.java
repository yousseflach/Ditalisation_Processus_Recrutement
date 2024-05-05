package ma.marjane.digitalisation_processus_recrutement.service;

import ma.marjane.digitalisation_processus_recrutement.entity.Collaborator;
import ma.marjane.digitalisation_processus_recrutement.repository.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CollaboratorService {

    @Autowired
    private CollaboratorRepository repository;

    public List<Collaborator> findAll() {
        return repository.findAll();
    }

    public Optional<Collaborator> findById(UUID id) {
        return repository.findById(id);
    }

    public Collaborator save(Collaborator collaborator) {
        return repository.save(collaborator);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
