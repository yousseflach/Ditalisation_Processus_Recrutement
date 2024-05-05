package ma.marjane.digitalisation_processus_recrutement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Internship extends Request{

    @Column(name = "nature_of_internship") // Maps the field to the specified column name in the database.
    private String natureOfInternship; // Nature du stage

    @Column(name = "duration_of_internship")
    private Integer durationOfInternship; // Durée du stage

    @Column(name = "content_of_internship", length = 1024) // Specifying a column definition.
    private String contentOfInternship; // Contenu du stage

    @Column(name = "expected_deliverable", length = 1024)
    private String expectedDeliverable; // Livrable attendu
}
