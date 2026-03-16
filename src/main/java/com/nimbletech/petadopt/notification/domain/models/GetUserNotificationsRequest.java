package com.nimbletech.petadopt.notification.domain.models;

public record GetUserNotificationsRequest(
        Long userId,
        boolean unreadOnly
) {
}