package ma.marjane.digitalisation_processus_recrutement.db1.repository;

import ma.marjane.digitalisation_processus_recrutement.db1.entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface TacheRepository extends JpaRepository<Tache, UUID> {
    @Transactional
    void deleteByDemandeId(UUID demandeId);

    List<Tache> findByDemandeId(UUID demandeId);

    List<Tache> findByDemandeIdAndEtape(UUID demandeid, String filtreCvs);
}
