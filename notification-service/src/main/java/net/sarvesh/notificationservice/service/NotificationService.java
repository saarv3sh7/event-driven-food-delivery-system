package net.sarvesh.notificationservice.service;

import lombok.RequiredArgsConstructor;
import net.sarvesh.notificationservice.entity.Notification;
import net.sarvesh.notificationservice.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void saveNotification(
            Long orderId,
            String title,
            String message,
            String type
    ) {

        Notification notification =
                Notification.builder()
                        .orderId(orderId)
                        .title(title)
                        .message(message)
                        .type(type)
                        .createdAt(Instant.now())
                        .build();

        notificationRepository.save(notification);
    }
}