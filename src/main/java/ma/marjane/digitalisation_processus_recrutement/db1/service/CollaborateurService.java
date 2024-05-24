package ma.marjane.digitalisation_processus_recrutement.db1.service;

import ma.marjane.digitalisation_processus_recrutement.db1.dto.CollaborateurDto;

import java.util.Optional;
import java.util.UUID;

public interface CollaborateurService extends BaseService<CollaborateurDto, UUID>{
    Optional<CollaborateurDto> findById(UUID id);

    CollaborateurDto save(CollaborateurDto collaborateurDto);

//    CollaborateurDto update(CollaborateurDto collaborateurDto);

    void deleteById(UUID id);
}
