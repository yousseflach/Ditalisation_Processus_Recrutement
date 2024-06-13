package ma.marjane.digitalisation_processus_recrutement.db1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "demande_stagaires")
public class Stage extends Demande {

    private String typeDeStage;
    private String formation;
    private String niveauEducation;
    private String ecolesouhetee;

    @Column(name = "natureDuStage")
    private String natureDuStage;

    @Column(name = "dureeDuStage")
    private Integer dureeDuStage;

    private LocalDateTime dateDeDebut;

    @Column(name = "contenuDuStage", length = 1024)
    private String contenuDuStage;

    @Column(name = "livrableAttendu", length = 1024)
    private String livrableAttendu;

}
