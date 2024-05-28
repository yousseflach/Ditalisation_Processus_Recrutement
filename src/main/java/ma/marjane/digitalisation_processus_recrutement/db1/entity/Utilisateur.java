package ma.marjane.digitalisation_processus_recrutement.db1.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "utilisateurs")
public class Utilisateur {

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

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateDeCreation;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime dateDeModification;

}
