package ma.marjane.digitalisation_processus_recrutement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @Column(unique = true, nullable = false)
    private String registrationNumber; // Matricule

    private String lastName; // Nom

    private String firstName; // Prénom

    private String organizationalUnit; // Unité organisationnelle

    private String department; // Direction

    private String corporateGroup; // Enseigne groupe

    private String emailAddress; // Adresse email

    private String managerRegistrationNumber; // Matricule N1

    private String managerRegistrationNumber2; // Matricule N2

    private String executiveCommitteeRegistrationNumber; // Matricule COMEX

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;
}
