package com.nimbletech.petadopt.notification.domain;

import com.nimbletech.petadopt.common.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class MarkAllNotificationsAsReadService implements Command<Long, Void> {
    
    private final NotificationRepository notificationRepository;

    @Override
    public ResponseEntity<Void> execute(Long userId) {
        int updated = notificationRepository.markAllAsRead(userId);
        log.info("Marked {} notifications as read for user {}", updated, userId);
        return ResponseEntity.ok().build();
    }
}
