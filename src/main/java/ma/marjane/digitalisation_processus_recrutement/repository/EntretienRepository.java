package ma.marjane.digitalisation_processus_recrutement.repository;

import ma.marjane.digitalisation_processus_recrutement.entity.Entretien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntretienRepository extends JpaRepository<Entretien, UUID> {
}
