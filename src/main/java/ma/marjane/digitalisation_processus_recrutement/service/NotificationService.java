package ma.marjane.digitalisation_processus_recrutement.service;

import ma.marjane.digitalisation_processus_recrutement.dto.NotificationDto;
import ma.marjane.digitalisation_processus_recrutement.dto.RoleDto;

import java.util.Optional;
import java.util.UUID;

public interface NotificationService extends BaseService<NotificationDto, UUID>{

    Optional<NotificationDto> findById(UUID id);

    NotificationDto save(NotificationDto notificationDto);

    NotificationDto update(NotificationDto notificationDto);

    void deleteById(UUID id);
}
