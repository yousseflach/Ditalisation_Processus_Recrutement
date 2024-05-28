package ma.marjane.digitalisation_processus_recrutement.db2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vue_recrutement", schema = "HR")  // Ajout du schéma HR
public class User {

    @Id
    @Column(name = "matricule")
    private String matricule;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "societe")
    private String societe;

    @Column(name = "code_etablissement")
    private String codeEtablissement;

    @Column(name = "etablissement")
    private String etablissement;

    @Column(name = "code_emploi")
    private String codeEmploi;

    @Column(name = "emploi")
    private String emploi;

    @Column(name = "code_uo")
    private String codeUo;

    @Column(name = "uo")
    private String uo;

    @Column(name = "mail")
    private String mail;

    @Column(name = "direction")
    private String direction;

    @Column(name = "manager1")
    private String manager1;

    @Column(name = "manager2")
    private String manager2;

    @Column(name = "affectation")
    private String affectation;

    @Column(name = "comex")
    private String comex;
}

