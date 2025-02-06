package org.locationtracker.service;


import lombok.RequiredArgsConstructor;
import org.locationtracker.dto.LocationUpdateDto;
import org.locationtracker.entity.LocationUpdate;
import org.locationtracker.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository repository;
    private double calculateHaversineDistance(LocationUpdate loc1, LocationUpdate loc2) {
        double lat1 = loc1.getLatitude();
        double lon1 = loc1.getLongitude();
        double lat2 = loc2.getLatitude();
        double lon2 = loc2.getLongitude();

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);

        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));

        return rad * c;
    }

    public double getTotalDistanceTraveled(String personId) {
        List<LocationUpdate> locations = repository.findByPersonIdOrderByTimestampAsc(personId);
        double totalDistance = 0;

        for (int i = 1; i < locations.size(); i++) {
            totalDistance += calculateHaversineDistance(locations.get(i - 1), locations.get(i));
        }

        return totalDistance;
    }

    public void save(LocationUpdateDto locationUpdateDto) {
        LocationUpdate locationUpdate = new LocationUpdate();
        locationUpdate.setLongitude(locationUpdateDto.getLongitude());
        locationUpdate.setLatitude(locationUpdateDto.getLatitude());
        locationUpdate.setPersonId(locationUpdateDto.getPersonId());
        repository.save(locationUpdate);
    }

}
