package org.locationtracker.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.mockito.Mockito.*;

@SpringJUnitConfig

public class ReportSchedulerTest {

    @Mock
    private LocationService locationService;

    @Test
    public void testGenerateReport() {
        String personId = "123";
        double mockDistance = 150.5;
        when(locationService.getTotalDistanceTraveled(personId)).thenReturn(mockDistance);

        ReportScheduler reportScheduler = new ReportScheduler(locationService);
        ScheduledAnnotationBeanPostProcessor postProcessor = new ScheduledAnnotationBeanPostProcessor();
        postProcessor.postProcessBeforeInitialization(reportScheduler, "reportScheduler");

        reportScheduler.generateReport();

        verify(locationService, times(1)).getTotalDistanceTraveled(personId);
    }
}
