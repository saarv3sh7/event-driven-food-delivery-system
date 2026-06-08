package net.sarvesh.commonlib.event;

import java.time.Instant;

public record PaymentSuccessEvent(

        Long orderId,

        String transactionId,

        Instant processedAt
) {
}