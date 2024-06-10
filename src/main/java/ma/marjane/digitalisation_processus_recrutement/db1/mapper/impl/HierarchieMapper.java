package ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl;


import ma.marjane.digitalisation_processus_recrutement.db1.dto.HierarchieDTO;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Hierarchie;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.BaseMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class HierarchieMapper implements BaseMapper<Hierarchie, HierarchieDTO> {
    ModelMapper modelMapper = new ModelMapper();
    @Override
    public Hierarchie convertToEntity(HierarchieDTO hierarchieDTO) {
        return modelMapper.map(hierarchieDTO, Hierarchie.class);
    }

    @Override
    public HierarchieDTO convertToDto(Hierarchie hierarchie) {
        return modelMapper.map(hierarchie, HierarchieDTO.class);
    }
}
