package ma.marjane.digitalisation_processus_recrutement.db1.repository;

import ma.marjane.digitalisation_processus_recrutement.db1.entity.Entretien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntretienRepository extends JpaRepository<Entretien, UUID> {
}
