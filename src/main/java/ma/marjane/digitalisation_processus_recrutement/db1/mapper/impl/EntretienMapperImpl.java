package ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl;

import ma.marjane.digitalisation_processus_recrutement.db1.dto.EntretienDto;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Entretien;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.BaseMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class EntretienMapperImpl implements BaseMapper<Entretien, EntretienDto> {

    ModelMapper modelMapper = new ModelMapper();
    // Customize ModelMapper configuration
    private void configureMapper() {
        TypeMap<EntretienDto, Entretien> typeMap = modelMapper.createTypeMap(EntretienDto.class, Entretien.class);
//        typeMap.addMapping(EntretienDto::getResponsableId, Entretien::setResponsableId);
//        typeMap.addMapping(EntretienDto::getCandidatId, Entretien::setCandidatId);
    }

    @Override
    public Entretien convertToEntity(EntretienDto interviewDto) {
        return modelMapper.map(interviewDto, Entretien.class);
    }

    @Override
    public EntretienDto convertToDto(Entretien interview) {
        return modelMapper.map(interview, EntretienDto.class);
    }
}
