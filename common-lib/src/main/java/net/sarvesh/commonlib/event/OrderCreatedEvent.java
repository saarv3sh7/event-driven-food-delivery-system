package net.sarvesh.commonlib.event;

import java.time.Instant;

public record OrderCreatedEvent(

        Long orderId,

        Long userId,

        Long restaurantId,

        Double amount,

        Instant createdAt
) {
}