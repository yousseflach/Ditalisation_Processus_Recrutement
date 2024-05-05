package ma.marjane.digitalisation_processus_recrutement.repository;

import ma.marjane.digitalisation_processus_recrutement.entity.DirectionOrStore;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DirectionOrStoreRepository extends JpaRepository<DirectionOrStore, UUID> {
}
