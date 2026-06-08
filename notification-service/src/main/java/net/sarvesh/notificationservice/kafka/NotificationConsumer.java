package net.sarvesh.notificationservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sarvesh.commonlib.constants.KafkaTopics;
import net.sarvesh.commonlib.event.OrderCreatedEvent;
import net.sarvesh.commonlib.event.PaymentFailedEvent;
import net.sarvesh.commonlib.event.PaymentSuccessEvent;
import net.sarvesh.notificationservice.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = KafkaTopics.ORDER_CREATED,
            groupId = "notification-group"
    )
    public void consumeOrderCreated(
            OrderCreatedEvent event
    ) {

        log.info(
                "Order created notification received: {}",
                event.orderId()
        );

        notificationService.saveNotification(
                event.orderId(),
                "Order Placed",
                "Order #" + event.orderId()
                        + " has been placed successfully.",
                "ORDER_CREATED"
        );
    }

    @KafkaListener(
            topics = KafkaTopics.PAYMENT_SUCCESS,
            groupId = "notification-group"
    )
    public void consumePaymentSuccess(
            PaymentSuccessEvent event
    ) {

        log.info(
                "Payment success notification received: {}",
                event.orderId()
        );

        notificationService.saveNotification(
                event.orderId(),
                "Payment Successful",
                "Payment completed successfully for order #"
                        + event.orderId(),
                "PAYMENT_SUCCESS"
        );
    }

    @KafkaListener(
            topics = KafkaTopics.PAYMENT_FAILED,
            groupId = "notification-group"
    )
    public void consumePaymentFailed(
            PaymentFailedEvent event
    ) {

        log.info(
                "Payment failed notification received: {}",
                event.orderId()
        );

        notificationService.saveNotification(
                event.orderId(),
                "Payment Failed",
                event.reason(),
                "PAYMENT_FAILED"
        );
    }
}