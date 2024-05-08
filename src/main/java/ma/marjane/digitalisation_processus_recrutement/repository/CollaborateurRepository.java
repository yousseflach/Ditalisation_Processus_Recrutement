package ma.marjane.digitalisation_processus_recrutement.repository;

import ma.marjane.digitalisation_processus_recrutement.entity.Collaborateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CollaborateurRepository extends JpaRepository<Collaborateur, UUID> {
}
