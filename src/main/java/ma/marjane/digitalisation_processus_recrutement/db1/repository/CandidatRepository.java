package ma.marjane.digitalisation_processus_recrutement.db1.repository;

import ma.marjane.digitalisation_processus_recrutement.db1.entity.Candidat;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, UUID> {
    @Transactional
    void deleteByDemandeId(UUID demandeId);

    List<Candidat> findByDemandeId(UUID demandeId);
}
