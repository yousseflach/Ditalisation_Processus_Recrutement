package ma.marjane.digitalisation_processus_recrutement.db1.service;

import ma.marjane.digitalisation_processus_recrutement.db1.dto.NotificationDto;

import java.util.Optional;
import java.util.UUID;

public interface NotificationService extends BaseService<NotificationDto, UUID>{

    Optional<NotificationDto> findById(UUID id);

    NotificationDto save(NotificationDto notificationDto);

//    NotificationDto update(NotificationDto notificationDto);

    void deleteById(UUID id);
}
