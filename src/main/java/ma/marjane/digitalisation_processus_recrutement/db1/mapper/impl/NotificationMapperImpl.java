package ma.marjane.digitalisation_processus_recrutement.db1.mapper.impl;

import ma.marjane.digitalisation_processus_recrutement.db1.dto.NotificationDto;
import ma.marjane.digitalisation_processus_recrutement.db1.entity.Notification;
import ma.marjane.digitalisation_processus_recrutement.db1.mapper.BaseMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapperImpl implements BaseMapper<Notification, NotificationDto> {

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public Notification convertToEntity(NotificationDto notificationDto) {
        return modelMapper.map(notificationDto, Notification.class);
    }

    @Override
    public NotificationDto convertToDto(Notification notification) {
        return modelMapper.map(notification, NotificationDto.class);
    }
}
