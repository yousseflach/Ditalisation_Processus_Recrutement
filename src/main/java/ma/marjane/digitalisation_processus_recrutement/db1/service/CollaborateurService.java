package ma.marjane.digitalisation_processus_recrutement.db1.service;

import ma.marjane.digitalisation_processus_recrutement.db1.dto.CollaborateurDto;

import java.util.UUID;

public interface CollaborateurService {

    CollaborateurDto save(CollaborateurDto collaborateurDto);

//    CollaborateurDto update(CollaborateurDto collaborateurDto);

    void deleteById(UUID id);
}
