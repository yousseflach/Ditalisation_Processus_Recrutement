package ma.marjane.digitalisation_processus_recrutement.db1.service;

import ma.marjane.digitalisation_processus_recrutement.db1.dto.RoleDto;

import java.util.Optional;
import java.util.UUID;

public interface RoleService extends BaseService<RoleDto, UUID>{

    Optional<RoleDto> findById(UUID id);

    RoleDto save(RoleDto roleDto);

//    RoleDto update(RoleDto roleDto);

    void deleteById(UUID id);
}
