package net.sarvesh.notificationservice.event;

public class PaymentFailedEvent {

    private Long orderId;
    private Long userId;
    private String reason;
}