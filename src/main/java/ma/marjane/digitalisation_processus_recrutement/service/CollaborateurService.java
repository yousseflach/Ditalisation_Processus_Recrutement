package ma.marjane.digitalisation_processus_recrutement.service;

import java.util.Optional;
import java.util.UUID;

public interface CollaborateurService extends BaseService<CollaborateurDto, UUID>{
    Optional<CollaborateurDto> findById(UUID id);

    CollaborateurDto save(CollaborateurDto collaborateurDto);

    CollaborateurDto update(CollaborateurDto collaborateurDto);

    void deleteById(UUID id);
}
