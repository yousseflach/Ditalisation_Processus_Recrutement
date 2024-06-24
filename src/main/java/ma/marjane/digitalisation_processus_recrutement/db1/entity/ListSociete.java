package ma.marjane.digitalisation_processus_recrutement.db1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "list_societes")
public class ListSociete {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String libelle;
    private  String societe;
}
