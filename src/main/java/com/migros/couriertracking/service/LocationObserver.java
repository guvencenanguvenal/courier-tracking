package com.migros.couriertracking.service;

import com.migros.couriertracking.model.Store;

import java.util.Date;

public interface LocationObserver {

    void notify(Store store, Double distance, Date date);

}
