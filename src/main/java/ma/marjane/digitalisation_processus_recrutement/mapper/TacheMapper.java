package ma.marjane.digitalisation_processus_recrutement.mapper;

import ma.marjane.digitalisation_processus_recrutement.dto.TacheDto;
import ma.marjane.digitalisation_processus_recrutement.entity.Tache;

public interface TacheMapper {

    Tache convertToEntity(TacheDto tacheDto);
    TacheDto convertToDto(Tache tache);

}
