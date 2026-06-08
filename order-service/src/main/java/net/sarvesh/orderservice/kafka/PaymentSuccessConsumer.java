package net.sarvesh.orderservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sarvesh.commonlib.event.PaymentSuccessEvent;
import net.sarvesh.orderservice.service.OrderStatusUpdateService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentSuccessConsumer {

    private final OrderStatusUpdateService service;

    @KafkaListener(
            topics = "payment-success",
            groupId = "order-group"
    )
    public void consume(
            PaymentSuccessEvent event
    ) {

        log.info(
                "Payment successful for order {}",
                event.orderId()
        );

        service.markConfirmed(
                event.orderId()
        );
    }
}