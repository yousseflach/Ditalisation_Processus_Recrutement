package ma.marjane.digitalisation_processus_recrutement.service;

import ma.marjane.digitalisation_processus_recrutement.entity.DirectionOuMagasin;
import ma.marjane.digitalisation_processus_recrutement.repository.DirectionOrStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DirectionOrStoreService {

    @Autowired
    private DirectionOrStoreRepository repository;

    public List<DirectionOuMagasin> findAll() {
        return repository.findAll();
    }

    public Optional<DirectionOuMagasin> findById(UUID id) {
        return repository.findById(id);
    }

    public DirectionOuMagasin save(DirectionOuMagasin directionOrStore) {
        return repository.save(directionOrStore);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
