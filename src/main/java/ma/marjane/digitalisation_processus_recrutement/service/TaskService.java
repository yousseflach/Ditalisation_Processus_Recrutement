package ma.marjane.digitalisation_processus_recrutement.service;

import ma.marjane.digitalisation_processus_recrutement.entity.Task;
import ma.marjane.digitalisation_processus_recrutement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Optional<Task> findById(UUID id) {
        return repository.findById(id);
    }

    public Task save(Task task) {
        return repository.save(task);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
