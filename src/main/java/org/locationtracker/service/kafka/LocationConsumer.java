package org.locationtracker.service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtracker.dto.LocationUpdateDto;
import org.locationtracker.service.LocationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocationConsumer {

    private final LocationService locationService;

    @KafkaListener(topics = "location-updates", groupId = "location-group")
    @Transactional
    public void consume(LocationUpdateDto locationUpdateDto) {
        locationService.save(locationUpdateDto);
        log.info("Saving location updates for personId {}:", locationUpdateDto.getPersonId());
    }

}
