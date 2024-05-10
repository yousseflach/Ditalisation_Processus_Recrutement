package ma.marjane.digitalisation_processus_recrutement.service;

import ma.marjane.digitalisation_processus_recrutement.entity.Tache;
import ma.marjane.digitalisation_processus_recrutement.repository.TâcheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TacheService {

    @Autowired
    private TâcheRepository repository;

    public List<Tache> findAll() {
        return repository.findAll();
    }

    public Optional<Tache> findById(UUID id) {
        return repository.findById(id);
    }

    public Tache save(Tache task) {
        return repository.save(task);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
