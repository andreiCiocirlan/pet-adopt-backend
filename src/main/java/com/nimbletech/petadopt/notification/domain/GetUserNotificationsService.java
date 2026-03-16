package com.nimbletech.petadopt.notification.domain;

import com.nimbletech.petadopt.common.Query;
import com.nimbletech.petadopt.notification.domain.models.GetUserNotificationsRequest;
import com.nimbletech.petadopt.notification.domain.models.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GetUserNotificationsService implements Query<GetUserNotificationsRequest, List<NotificationDto>> {

    private final NotificationRepository notificationRepository;

    @Override
    public ResponseEntity<List<NotificationDto>> execute(GetUserNotificationsRequest request) {
        List<Notification> notifications = request.unreadOnly()
                ? notificationRepository.findByUserIdAndIsReadFalseOrderByCreatedAtDesc(request.userId())
                : notificationRepository.findByUserIdOrderByCreatedAtDesc(request.userId());

        List<NotificationDto> dtos = notifications.stream()
                .map(NotificationMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}
