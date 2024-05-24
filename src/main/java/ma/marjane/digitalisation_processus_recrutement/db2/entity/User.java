package ma.marjane.digitalisation_processus_recrutement.db2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vue_recrutement", schema = "HR")  // Ajout du schéma HR
public class User {

    @Id
    private String matricule;
    private String nom;
    private String prenom;
    private String societe;
    private String codeEtablissement;
    private String etablissement;
    private String codeEmploi;
    private String emploi;
    private String codeUo;
    private String uo;
    private String mail;
    private String direction;
    private String manager1;
    private String manager2;
    private String affectation;
    private String comex;
}

