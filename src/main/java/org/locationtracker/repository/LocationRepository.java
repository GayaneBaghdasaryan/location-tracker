package org.locationtracker.repository;


import org.locationtracker.entity.LocationUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationUpdate, Long> {

    List<LocationUpdate> findByPersonIdOrderByTimestampAsc(String personId);
}
