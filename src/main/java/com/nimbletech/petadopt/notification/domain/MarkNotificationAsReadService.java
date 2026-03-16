package com.nimbletech.petadopt.notification.domain;

import com.nimbletech.petadopt.common.Command;
import com.nimbletech.petadopt.notification.domain.models.MarkNotificationAsReadRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class MarkNotificationAsReadService implements Command<MarkNotificationAsReadRequest, Void> {
    
    private final NotificationRepository notificationRepository;

    @Override
    public ResponseEntity<Void> execute(MarkNotificationAsReadRequest request) {
        Optional<Notification> notificationOpt = notificationRepository.findById(request.notificationId());
        notificationOpt.ifPresentOrElse(
            notification -> {
                if (!notification.getUserId().equals(request.userId())) {
                    throw new IllegalArgumentException("Notification does not belong to user");
                }
                notification.setRead(true);
                notificationRepository.save(notification);
            },
            () -> { throw new EntityNotFoundException("Notification not found"); }
        );
        
        return ResponseEntity.ok().build();
    }
}