package ma.marjane.digitalisation_processus_recrutement.db1.service;

import ma.marjane.digitalisation_processus_recrutement.db1.dto.TacheDto;

import java.util.Optional;
import java.util.UUID;

public interface TacheService extends BaseService<TacheDto, UUID>{

    Optional<TacheDto> findById(UUID id);

    TacheDto save(TacheDto tacheDto);

//    TacheDto update(TacheDto tacheDto);

    void deleteById(UUID id);

    void deleteByDemandeId(UUID demandeId);
}
