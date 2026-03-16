package com.nimbletech.petadopt.notification.web;

import com.nimbletech.petadopt.auth.CustomUserDetails;
import com.nimbletech.petadopt.notification.domain.GetUnreadCountService;
import com.nimbletech.petadopt.notification.domain.GetUserNotificationsService;
import com.nimbletech.petadopt.notification.domain.MarkAllNotificationsAsReadService;
import com.nimbletech.petadopt.notification.domain.MarkNotificationAsReadService;
import com.nimbletech.petadopt.notification.domain.models.GetUserNotificationsRequest;
import com.nimbletech.petadopt.notification.domain.models.MarkNotificationAsReadRequest;
import com.nimbletech.petadopt.notification.domain.models.NotificationCountDto;
import com.nimbletech.petadopt.notification.domain.models.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Validated
public class NotificationController {

    private final GetUserNotificationsService getUserNotificationsService;
    private final GetUnreadCountService getUnreadCountService;
    private final MarkNotificationAsReadService markNotificationAsReadService;
    private final MarkAllNotificationsAsReadService markAllNotificationsAsReadService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<NotificationDto>> getUserNotifications(
            @RequestParam(defaultValue = "false") boolean unreadOnly,
            Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        GetUserNotificationsRequest request = new GetUserNotificationsRequest(userId, unreadOnly);
        return getUserNotificationsService.execute(request);
    }

    @GetMapping("/count")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<NotificationCountDto> getUnreadCount(Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        return getUnreadCountService.execute(userId);
    }

    @PostMapping("/{notificationId}/read")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> markAsRead(
            @PathVariable UUID notificationId,
            Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        MarkNotificationAsReadRequest request = new MarkNotificationAsReadRequest(notificationId, userId);
        return markNotificationAsReadService.execute(request);
    }

    @PostMapping("/read-all")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> markAllAsRead(Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        return markAllNotificationsAsReadService.execute(userId);
    }

    private Long getUserIdFromAuthentication(Authentication authentication) {
        return extractUserIdFromPrincipal(authentication.getPrincipal());
    }

    private Long extractUserIdFromPrincipal(Object principal) {
        if (principal instanceof CustomUserDetails userDetails) {
            return userDetails.getId();
        }
        throw new IllegalStateException("Authenticated principal is not a CustomUserDetails instance");
    }

}

