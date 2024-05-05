package ma.marjane.digitalisation_processus_recrutement.repository;

import ma.marjane.digitalisation_processus_recrutement.entity.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CollaboratorRepository extends JpaRepository<Collaborator, UUID> {
}
