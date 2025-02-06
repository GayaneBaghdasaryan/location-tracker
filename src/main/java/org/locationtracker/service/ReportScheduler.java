package org.locationtracker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReportScheduler {

    private final LocationService locationService;

    @Scheduled(cron = "${location.report-cron-job}") // Every day at midnight (00:00)
    public void generateReport() {
        String personId = "123";
        double totalDistance = locationService.getTotalDistanceTraveled(personId);
        log.info("Total Distance traveled by person {} : {} km {}", personId, totalDistance, LocalDate.now());
    }
}
