package ma.marjane.digitalisation_processus_recrutement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Collaborator extends Request {

    @Column(name = "site_attachment")
    private String siteAttachment; // Site de rattachement

    @Column(name = "creation_reason")
    private String creationReason; // Motif de création

    @Column(name = "position_budgeted")
    private Boolean positionBudgeted; // Budgétisation du poste

    @Column(name = "estimated_budget")
    private Number estimatedBudget; // Estimation du budget

    @Column(name = "departure_reason")
    private String departureReason; // Motif de départ

    @Column(name = "functional_supervisor")
    private String functionalSupervisor; // Supérieur fonctionnel

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> hierarchicalRelations; // Relations hiérarchiques

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> functionalRelations; // Relations fonctionnelles

    @Column(name = "global_mission", length = 1024)
    private String globalMission; // Mission globale

    @Column(name = "main_activities", length = 1024)
    private String mainActivities; // Principales activités

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> quantitativeIndicators; // Indicateurs quantitatifs

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> qualitativeIndicators; // Indicateurs qualitatifs

    @Column(name = "contract_type")
    private String contractType; // Type de contrat

    @Column(name = "category")
    private String category; // Catégorie

    @Column(name = "recruitment_type")
    private String recruitmentType; // Type de recrutement

}
