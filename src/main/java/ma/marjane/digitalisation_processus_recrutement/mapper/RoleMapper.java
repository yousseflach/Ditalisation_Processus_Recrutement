package ma.marjane.digitalisation_processus_recrutement.mapper;

import ma.marjane.digitalisation_processus_recrutement.dto.RoleDto;
import ma.marjane.digitalisation_processus_recrutement.entity.Role;

public interface RoleMapper {

    Role convertToEntity(RoleDto roleDto);
    RoleDto convertToDto(Role role);


}
