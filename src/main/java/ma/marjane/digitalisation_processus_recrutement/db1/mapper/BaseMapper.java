package ma.marjane.digitalisation_processus_recrutement.db1.mapper;

public interface BaseMapper <ENTITY, DTO>{

    ENTITY convertToEntity(DTO dto);
    DTO convertToDto(ENTITY entity);
}
