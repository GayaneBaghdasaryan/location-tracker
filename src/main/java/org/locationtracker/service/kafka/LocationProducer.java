package org.locationtracker.service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtracker.dto.LocationUpdateDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationProducer {

    private final KafkaTemplate<String, LocationUpdateDto> kafkaTemplate;
    private final Random random = new Random();

    @Scheduled(fixedRate = 3600000)  // Every 60 minutes (1 hour)
    public void sendLocationUpdate() {
        String personId = "123";

        double latitude = 37.7749 + (random.nextDouble() - 0.5) * 0.01;
        double longitude = -122.4194 + (random.nextDouble() - 0.5) * 0.01;
        LocationUpdateDto locationUpdate = new LocationUpdateDto(
                personId, latitude, longitude
        );

        kafkaTemplate.send("location-updates", personId, locationUpdate);
        System.out.println("Sent location update: " + locationUpdate);
    }
}
