package com.migros.couriertracking.service;

import com.migros.couriertracking.model.Courier;
import com.migros.couriertracking.model.Store;
import com.migros.couriertracking.model.Stores;
import com.migros.couriertracking.util.DateUtil;
import com.migros.couriertracking.util.DistanceCalculator;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CourierTracker implements Tracker{

    private List<LocationObserver> observers = new ArrayList<>();

    private static final Double MAX_DISTANCE = 100.0;
    private static final Integer OVER_MINUTE = 1;

    private DistanceCalculator distanceCalculator;
    private Courier trackedCourier;
    private Date lastUpdateDate;
    private Store nearStore;

    public CourierTracker(Courier trackedCourier, DistanceCalculator distanceCalculator) {
        this.trackedCourier = trackedCourier;
        this.distanceCalculator = distanceCalculator;
    }

    @Override
    public void addObserver(final LocationObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObserver() {
        for (LocationObserver observer : observers) {
            observer.notify(this.nearStore,
                    this.distanceCalculator.getDistanceBetweenTwoPoints(
                            this.trackedCourier.getLat(),
                            this.trackedCourier.getLng(),
                            this.nearStore.getLat(),
                            this.nearStore.getLng()),
                    this.lastUpdateDate);
        }
    }

    public void updateLocation(Double lat, Double lng) {
        this.trackedCourier.setLat(lat);
        this.trackedCourier.setLng(lng);

        for (Store store : Stores.getInstance()) {
            Double distance = distanceCalculator.getDistanceBetweenTwoPoints(lat, lng, store.getLat(), store.getLng());
            if (distance < MAX_DISTANCE) {
                this.nearStore = store;
                if (this.lastUpdateDate != null) {
                    long diffInMilliSec = Math.abs(DateUtil.today().getTime() - this.lastUpdateDate.getTime());
                    long diff = TimeUnit.MINUTES.convert(diffInMilliSec, TimeUnit.MILLISECONDS);

                    if (diff > OVER_MINUTE) {
                        this.lastUpdateDate = DateUtil.today();
                        this.notifyObserver();
                    }
                } else {
                    this.lastUpdateDate = DateUtil.today();
                    this.notifyObserver();
                }
            }
        }

    }

}
