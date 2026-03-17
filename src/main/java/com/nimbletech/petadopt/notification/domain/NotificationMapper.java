package com.nimbletech.petadopt.notification.domain;

import com.nimbletech.petadopt.notification.domain.models.NotificationDto;
import org.springframework.stereotype.Component;

@Component
class NotificationMapper {
    
    public static NotificationDto toDto(Notification notification) {
        return new NotificationDto(
                notification.getId(),
                notification.getUserId(),
                notification.getAppointmentId(),
                notification.getMessage(),
                notification.isRead(),
                notification.getCreatedAt()
        );
    }
}
