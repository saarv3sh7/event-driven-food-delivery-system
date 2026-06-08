package net.sarvesh.orderservice.kafka;

import lombok.RequiredArgsConstructor;
import net.sarvesh.commonlib.event.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventPublisher {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public void publish(OrderCreatedEvent event) {

        kafkaTemplate.send(
                "order-created",
                event.orderId().toString(),
                event
        );
    }
}