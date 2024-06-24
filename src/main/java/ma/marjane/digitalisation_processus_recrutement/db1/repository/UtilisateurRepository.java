package ma.marjane.digitalisation_processus_recrutement.db1.repository;

import ma.marjane.digitalisation_processus_recrutement.db1.entity.Utilisateur;
import ma.marjane.digitalisation_processus_recrutement.db1.record.Listesousdirection;
import ma.marjane.digitalisation_processus_recrutement.db1.record.NomPrenomMatricule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {

    Utilisateur findByMail(String mail);

    Utilisateur findByMatricule(String matricule);

//    @Query("SELECT u FROM Utilisateur u WHERE u.direction LIKE %?1%")
//    List<Utilisateur> findByDirection(String direction);

    @Query("SELECT DISTINCT u.societe FROM Utilisateur u where u.societe is not null")
    List<String> findAllBySociete();

    @Query("SELECT DISTINCT new ma.marjane.digitalisation_processus_recrutement.db1.record.Listesousdirection(u.code_uo,u.uo) FROM Utilisateur u where u.uo is not null and u.manager1= ?1")
    List<Listesousdirection> findAllUO(String matricule);


    @Query("SELECT new ma.marjane.digitalisation_processus_recrutement.db1.record.NomPrenomMatricule(u.nom, u.prenom, u.matricule) FROM Utilisateur u where u.direction = ?1")
    List<NomPrenomMatricule> findAllNomPrenomMatricule(String direction);

    //get all emplois by code_uo
    @Query("SELECT DISTINCT u.emploi FROM Utilisateur u where u.code_uo = ?1")
    List<String> findAllEmploiByCodeUO(String code_uo);

    //get all emplois by etablissement or direction
    @Query("SELECT DISTINCT u.emploi FROM Utilisateur u where u.direction = ?1 or u.etablissement = ?1")
    List<String> findAllEmploiByDirectionOrEtablissement(String direction);


}

