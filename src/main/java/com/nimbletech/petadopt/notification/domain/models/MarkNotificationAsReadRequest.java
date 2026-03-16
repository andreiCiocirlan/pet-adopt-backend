package com.nimbletech.petadopt.notification.domain.models;

import java.util.UUID;

public record MarkNotificationAsReadRequest(
        UUID notificationId,
        Long userId
) {
}