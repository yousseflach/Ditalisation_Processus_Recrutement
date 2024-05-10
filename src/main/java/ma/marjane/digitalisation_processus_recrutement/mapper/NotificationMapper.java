package ma.marjane.digitalisation_processus_recrutement.mapper;

import ma.marjane.digitalisation_processus_recrutement.dto.NotificationDto;
import ma.marjane.digitalisation_processus_recrutement.entity.Notification;

public interface NotificationMapper {

    Notification convertToEntity(NotificationDto notificationDto);
    NotificationDto convertToDto(Notification notification);

}
