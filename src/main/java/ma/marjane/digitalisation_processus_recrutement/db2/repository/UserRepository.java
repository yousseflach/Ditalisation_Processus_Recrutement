//package ma.marjane.digitalisation_processus_recrutement.db2.repository;
//
//import jakarta.transaction.Transactional;
//import ma.marjane.digitalisation_processus_recrutement.db2.entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//@Transactional()
//public interface UserRepository extends JpaRepository<User, String> {
//
//    @Query("SELECT u FROM User u")  // Utilisation de JPQL pour sélectionner toutes les entrées de l'entité Recrutement
//    List<User> findAllUsers();
//}
