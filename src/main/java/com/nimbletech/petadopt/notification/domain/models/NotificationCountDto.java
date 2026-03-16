package com.nimbletech.petadopt.notification.domain.models;

public record NotificationCountDto(
        int unreadCount,
        int totalCount
) {
}