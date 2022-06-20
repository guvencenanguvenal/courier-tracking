package com.migros.couriertracking.service;

import com.migros.couriertracking.model.Courier;
import com.migros.couriertracking.util.DistanceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TrackerFactory {

    @Autowired
    DistanceCalculator distanceCalculator;

    private static final HashMap<Long, CourierTracker> courierTrackerMap = new HashMap<>();

    public void createTracker(Courier courier) {
        courierTrackerMap.put(courier.getId(), new CourierTracker(courier, distanceCalculator));
    }

    public void deleteTracker(Long id) {
        courierTrackerMap.remove(id);
    }

    public CourierTracker getCourierTracker(Long courierId) {
        return (CourierTracker) courierTrackerMap.get(courierId);
    }

}
