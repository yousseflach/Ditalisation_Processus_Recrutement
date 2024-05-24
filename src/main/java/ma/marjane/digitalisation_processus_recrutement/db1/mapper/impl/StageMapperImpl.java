package ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl;

import ma.marjane.digitalisation_processus_recrutement.db1.dto.StageDto;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.BaseMapper;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Stage;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StageMapperImpl implements BaseMapper<Stage, StageDto> {

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
