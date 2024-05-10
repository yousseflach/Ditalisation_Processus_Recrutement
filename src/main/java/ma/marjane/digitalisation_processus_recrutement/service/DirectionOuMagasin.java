package ma.marjane.digitalisation_processus_recrutement.service;

import ma.marjane.digitalisation_processus_recrutement.repository.DirectionOuMagasinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DirectionOuMagasin {

    @Autowired
    private DirectionOuMagasinRepository repository;

    public List<ma.marjane.digitalisation_processus_recrutement.entity.DirectionOuMagasin> findAll() {
        return repository.findAll();
    }

    public Optional<ma.marjane.digitalisation_processus_recrutement.entity.DirectionOuMagasin> findById(UUID id) {
        return repository.findById(id);
    }

    public ma.marjane.digitalisation_processus_recrutement.entity.DirectionOuMagasin save(ma.marjane.digitalisation_processus_recrutement.entity.DirectionOuMagasin directionOrStore) {
        return repository.save(directionOrStore);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
