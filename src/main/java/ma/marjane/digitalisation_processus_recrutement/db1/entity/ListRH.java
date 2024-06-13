package ma.marjane.digitalisation_processus_recrutement.db1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "listrh")
public class ListRH {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String mail;
    private String matricule;
    private String nom;
    private String prenom;
}
