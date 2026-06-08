package net.sarvesh.paymentservice.config;

import net.sarvesh.commonlib.event.OrderCreatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;


@Configuration
public class KafkaConsumerConfig {

    @Bean
    public JsonDeserializer<OrderCreatedEvent> orderCreatedEventJsonDeserializer() {

        JsonDeserializer<OrderCreatedEvent> deserializer =
                new JsonDeserializer<>(OrderCreatedEvent.class);

        deserializer.addTrustedPackages(
                "net.sarvesh.commonlib.event"
        );

        return deserializer;
    }
}