package com.nimbletech.petadopt.notification.domain;

import com.nimbletech.petadopt.common.Query;
import com.nimbletech.petadopt.notification.domain.models.NotificationCountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetUnreadCountService implements Query<Long, NotificationCountDto> {

    private final NotificationRepository notificationRepository;

    @Override
    public ResponseEntity<NotificationCountDto> execute(Long userId) {
        long unreadCount = notificationRepository.findByUserIdAndIsReadFalseOrderByCreatedAtDesc(userId).size();
        long totalCount = notificationRepository.findByUserIdOrderByCreatedAtDesc(userId).size();

        NotificationCountDto dto = new NotificationCountDto((int) unreadCount, (int) totalCount);

        return ResponseEntity.ok(dto);
    }
}
