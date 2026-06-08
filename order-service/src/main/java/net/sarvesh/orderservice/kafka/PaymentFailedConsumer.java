package net.sarvesh.orderservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sarvesh.commonlib.event.PaymentFailedEvent;
import net.sarvesh.orderservice.service.OrderStatusUpdateService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentFailedConsumer {

    private final OrderStatusUpdateService service;

    @KafkaListener(
            topics = "payment-failed",
            groupId = "order-group"
    )
    public void consume(
            PaymentFailedEvent event
    ) {

        log.info(
                "Payment failed for order {}",
                event.orderId()
        );

        service.markFailed(
                event.orderId()
        );
    }
}