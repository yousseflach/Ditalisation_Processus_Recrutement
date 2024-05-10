package ma.marjane.digitalisation_processus_recrutement.mapper;

import ma.marjane.digitalisation_processus_recrutement.dto.CollaborateurDto;
import ma.marjane.digitalisation_processus_recrutement.entity.Collaborateur;

public interface CollaborateurMapper {

    Collaborateur convertToEntity(CollaborateurDto collaborateurDto);
    CollaborateurDto convertToDto(Collaborateur collaborateur);

}
