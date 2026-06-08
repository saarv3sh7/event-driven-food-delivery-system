package net.sarvesh.paymentservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sarvesh.commonlib.event.OrderCreatedEvent;
import net.sarvesh.commonlib.event.PaymentFailedEvent;
import net.sarvesh.commonlib.event.PaymentSuccessEvent;
import net.sarvesh.paymentservice.Entity.Payment;
import net.sarvesh.paymentservice.Entity.PaymentStatus;
import net.sarvesh.paymentservice.Repository.PaymentRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderCreatedConsumer {

    private final PaymentRepository paymentRepository;
    private final PaymentEventPublisher paymentEventPublisher;

    @KafkaListener(
            topics = "order-created",
            groupId = "payment-group"
    )
    public void consume(OrderCreatedEvent event) {

        try {

            log.info("Received order event : {}", event.orderId());

            boolean success =
                    ThreadLocalRandom.current()
                            .nextInt(100) > 20;

            if (success) {

                String transactionId = UUID.randomUUID().toString();

                Payment payment =
                        Payment.builder()
                                .orderId(event.orderId())
                                .transactionId(transactionId)
                                .amount(event.amount())
                                .status(PaymentStatus.valueOf("SUCCESS"))
                                .build();

                paymentRepository.save(payment);

                paymentEventPublisher.publishSuccess(
                        new PaymentSuccessEvent(
                                event.orderId(),
                                transactionId,
                                Instant.now()
                        )
                );

            } else {

                paymentEventPublisher.publishFailure(
                        new PaymentFailedEvent(
                                event.orderId(),
                                "Bank declined payment",
                                Instant.now()
                        )
                );
            }

        } catch (Exception e) {

            log.error("Consumer failed for orderId: {}", event.orderId(), e);
        }
    }
}