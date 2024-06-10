package ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl;

import ma.marjane.digitalisation_processus_recrutement.db1.dto.RoleDto;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Role;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.BaseMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapperImpl implements BaseMapper<Role, RoleDto> {

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
