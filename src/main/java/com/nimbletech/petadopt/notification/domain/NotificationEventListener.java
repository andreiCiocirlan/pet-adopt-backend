package com.nimbletech.petadopt.notification.domain;

import com.nimbletech.petadopt.notification.domain.models.AppointmentStatusChangedEvent;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
class NotificationEventListener {

    private final NotificationRepository notificationRepository;

    public NotificationEventListener(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @ApplicationModuleListener
    public void handleAppointmentStatusChange(AppointmentStatusChangedEvent event) {
        Notification notification = new Notification(event.userId(), event.appointmentId(), event.message());
        notificationRepository.save(notification);
    }

}
