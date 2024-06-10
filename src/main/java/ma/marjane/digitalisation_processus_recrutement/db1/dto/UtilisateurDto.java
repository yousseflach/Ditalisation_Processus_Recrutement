package ma.marjane.digitalisation_processus_recrutement.db1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDto {

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
    private String role;
}
