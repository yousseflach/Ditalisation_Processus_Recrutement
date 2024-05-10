package ma.marjane.digitalisation_processus_recrutement.mapper.impl;

import ma.marjane.digitalisation_processus_recrutement.dto.NotificationDto;
import ma.marjane.digitalisation_processus_recrutement.entity.Notification;
import ma.marjane.digitalisation_processus_recrutement.mapper.NotificationMapper;
import org.modelmapper.ModelMapper;

public class NotificationMapperImpl implements NotificationMapper {

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
