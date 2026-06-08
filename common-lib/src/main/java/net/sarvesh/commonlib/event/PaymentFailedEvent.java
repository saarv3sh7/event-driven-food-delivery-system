package net.sarvesh.commonlib.event;

import java.time.Instant;

public record PaymentFailedEvent(

        Long orderId,

        String reason,

        Instant processedAt
) {
}