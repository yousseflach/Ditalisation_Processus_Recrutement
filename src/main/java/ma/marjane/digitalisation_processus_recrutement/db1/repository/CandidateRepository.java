package ma.marjane.digitalisation_processus_recrutement.db1.repository;

import ma.marjane.digitalisation_processus_recrutement.db1.entity.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CandidateRepository extends JpaRepository<Candidat, UUID> {
}
