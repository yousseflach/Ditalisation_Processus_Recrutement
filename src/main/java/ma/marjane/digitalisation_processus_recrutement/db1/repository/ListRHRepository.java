package ma.marjane.digitalisation_processus_recrutement.db1.repository;

import ma.marjane.digitalisation_processus_recrutement.db1.entity.ListRH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
@EnableJpaRepositories
public interface ListRHRepository extends JpaRepository<ListRH, UUID>{
     ListRH findByMatricule(String matricule);

}
