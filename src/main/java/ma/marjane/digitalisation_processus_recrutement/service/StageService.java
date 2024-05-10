package ma.marjane.digitalisation_processus_recrutement.service;

import ma.marjane.digitalisation_processus_recrutement.entity.Stage;
import ma.marjane.digitalisation_processus_recrutement.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StageService {

    @Autowired
    private StageRepository repository;

    public List<Stage> findAll() {
        return repository.findAll();
    }

    public Optional<Stage> findById(UUID id) {
        return repository.findById(id);
    }

    public Stage save(Stage internship) {
        return repository.save(internship);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
