package ma.marjane.digitalisation_processus_recrutement.db1.repository;



import ma.marjane.digitalisation_processus_recrutement.db1.entity.Hierarchie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HierarchieRepository extends JpaRepository<Hierarchie, UUID> {

    List<Hierarchie> findByMatricule(String matricule);
    List<Hierarchie> findByDemandeId(UUID demandeid);

    List<Hierarchie> findByMatriculeAndStatut( String matricule, String statut);

    List<Hierarchie> findByDemandeIdAndMatricule(UUID demandeId, String matricule);

    Hierarchie findByDemandeIdAndMatriculeAndStatut(UUID demandeId, String matricule, String enCours);

    Hierarchie findByDemandeIdAndStatut(UUID demandeid, String Statut);
}