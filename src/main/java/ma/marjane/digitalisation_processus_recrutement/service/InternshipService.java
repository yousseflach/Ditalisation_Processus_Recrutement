package ma.marjane.digitalisation_processus_recrutement.service;

import ma.marjane.digitalisation_processus_recrutement.entity.Internship;
import ma.marjane.digitalisation_processus_recrutement.repository.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InternshipService {

    @Autowired
    private InternshipRepository repository;

    public List<Internship> findAll() {
        return repository.findAll();
    }

    public Optional<Internship> findById(UUID id) {
        return repository.findById(id);
    }

    public Internship save(Internship internship) {
        return repository.save(internship);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
