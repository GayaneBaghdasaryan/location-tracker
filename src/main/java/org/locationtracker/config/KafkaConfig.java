package org.locationtracker.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic locationUpdatesTopic() {
        return new NewTopic("location-updates", 1, (short) 1);
    }

}
