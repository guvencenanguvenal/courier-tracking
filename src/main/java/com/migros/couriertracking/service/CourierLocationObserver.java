package com.migros.couriertracking.service;

import com.migros.couriertracking.model.Store;
import com.migros.couriertracking.model.Stores;
import com.migros.couriertracking.util.DistanceCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class CourierLocationObserver implements LocationObserver {

    @Override
    public void notify(Store store, Double distance, Date date) {
        log.info("Courier location under 100 meters for store " + store.getName() + " - Distance : " + distance);
    }
}
