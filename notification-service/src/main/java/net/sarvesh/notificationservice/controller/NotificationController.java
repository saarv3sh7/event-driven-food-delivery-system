package net.sarvesh.notificationservice.controller;

import lombok.RequiredArgsConstructor;
import net.sarvesh.notificationservice.entity.Notification;
import net.sarvesh.notificationservice.repository.NotificationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationRepository repository;

    @GetMapping
    public List<Notification> getAllNotifications() {

        return repository.findAll();
    }

    @GetMapping("/order/{orderId}")
    public List<Notification> getByOrderId(
            @PathVariable Long orderId
    ) {

        return repository.findByOrderId(orderId);
    }
}