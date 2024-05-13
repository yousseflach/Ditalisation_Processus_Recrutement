package ma.marjane.digitalisation_processus_recrutement.service;

import ma.marjane.digitalisation_processus_recrutement.dto.UtilisateurDto;

import java.util.Optional;
import java.util.UUID;

public interface UtilisateurService extends BaseService<UtilisateurDto, UUID>{

    Optional<UtilisateurDto> findById(UUID id);

    UtilisateurDto save(UtilisateurDto utilisateurDto);

    UtilisateurDto update(UtilisateurDto utilisateurDto);

    void deleteById(UUID id);
}