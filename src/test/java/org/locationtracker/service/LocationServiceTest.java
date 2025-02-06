package org.locationtracker.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.locationtracker.entity.LocationUpdate;
import org.locationtracker.repository.LocationRepository;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService;

    @Test
    public void testGetTotalDistanceTraveled() {
        String personId = "123";
        LocationUpdate loc1 = new LocationUpdate();
        loc1.setLatitude(37.7749);
        loc1.setLongitude(-122.4194);
        loc1.setPersonId(personId);

        LocationUpdate loc2 = new LocationUpdate();
        loc2.setLatitude(37.7750);
        loc2.setLongitude(-122.4195);
        loc2.setPersonId(personId);

        when(locationRepository.findByPersonIdOrderByTimestampAsc(personId)).thenReturn(List.of(loc1, loc2));

        double totalDistance = locationService.getTotalDistanceTraveled(personId);

        assertEquals(0.014, totalDistance, 0.001);
    }
}
