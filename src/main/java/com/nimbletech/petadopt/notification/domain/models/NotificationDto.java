package com.nimbletech.petadopt.notification.domain.models;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationDto(
        UUID id,
        Long userId,
        Long appointmentId,
        String message,
        boolean isRead,
        LocalDateTime createdAt
) {
}


