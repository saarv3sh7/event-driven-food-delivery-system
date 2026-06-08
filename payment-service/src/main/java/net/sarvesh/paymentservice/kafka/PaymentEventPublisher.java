package net.sarvesh.paymentservice.kafka;

import lombok.RequiredArgsConstructor;
import net.sarvesh.commonlib.event.PaymentFailedEvent;
import net.sarvesh.commonlib.event.PaymentSuccessEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishSuccess(
            PaymentSuccessEvent event
    ) {

        kafkaTemplate.send(
                "payment-success",
                event.orderId().toString(),
                event
        );
    }

    public void publishFailure(
            PaymentFailedEvent event
    ) {

        kafkaTemplate.send(
                "payment-failed",
                event.orderId().toString(),
                event
        );
    }
}