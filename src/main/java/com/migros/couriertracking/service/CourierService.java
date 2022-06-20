package com.migros.couriertracking.service;

import com.migros.couriertracking.model.Courier;
import com.migros.couriertracking.model.CourierLocationLog;

import java.util.List;

public interface CourierService {

    Courier addCourier(Courier courier);

    void deleteCourier(Long id);

    Courier updateCourier(Courier courier);

    Courier getCourier(Long id);

    List<Courier> getAllCourier();

    List<CourierLocationLog> getAllCourierLocation(Long id);

    Double getTotalTravelDistance(Long id);

}
