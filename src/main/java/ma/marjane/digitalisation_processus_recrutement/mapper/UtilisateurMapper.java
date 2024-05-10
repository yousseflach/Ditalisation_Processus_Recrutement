package ma.marjane.digitalisation_processus_recrutement.mapper;

import ma.marjane.digitalisation_processus_recrutement.dto.UtilisateurDto;
import ma.marjane.digitalisation_processus_recrutement.entity.Utilisateur;

public interface UtilisateurMapper {

    Utilisateur convertToEntity(UtilisateurDto utilisateurDto);
    UtilisateurDto convertToDto(Utilisateur utilisateur);

}
