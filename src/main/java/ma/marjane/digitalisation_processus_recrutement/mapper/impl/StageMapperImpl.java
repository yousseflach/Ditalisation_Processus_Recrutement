package ma.marjane.digitalisation_processus_recrutement.mapper.impl;

import ma.marjane.digitalisation_processus_recrutement.dto.StageDto;
import ma.marjane.digitalisation_processus_recrutement.entity.Stage;
import ma.marjane.digitalisation_processus_recrutement.mapper.StageMapper;
import org.modelmapper.ModelMapper;

public class StageMapperImpl implements StageMapper {

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public Stage convertToEntity(StageDto stageDto) {
        return modelMapper.map(stageDto, Stage.class);
    }

    @Override
    public StageDto convertToDto(Stage stage) {
        return modelMapper.map(stage, StageDto.class);
    }
}
