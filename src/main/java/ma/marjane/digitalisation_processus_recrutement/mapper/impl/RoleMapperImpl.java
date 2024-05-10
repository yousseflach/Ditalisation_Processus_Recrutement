package ma.marjane.digitalisation_processus_recrutement.mapper.impl;

import ma.marjane.digitalisation_processus_recrutement.dto.RoleDto;
import ma.marjane.digitalisation_processus_recrutement.entity.Role;
import ma.marjane.digitalisation_processus_recrutement.mapper.RoleMapper;
import org.modelmapper.ModelMapper;

public class RoleMapperImpl implements RoleMapper {

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public Role convertToEntity(RoleDto roleDto) {
        return modelMapper.map(roleDto, Role.class);
    }

    @Override
    public RoleDto convertToDto(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }
}
