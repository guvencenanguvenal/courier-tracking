package com.migros.couriertracking.service;

import com.migros.couriertracking.model.Courier;
import com.migros.couriertracking.repository.CourierRepository;
import com.migros.couriertracking.util.DistanceCalculator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class CourierDistanceStoreLogServiceTest {

    @Autowired
    CourierServiceImpl courierService;

    @Autowired
    DistanceCalculator distanceCalculator;

    @MockBean
    CourierRepository courierRepository;

    @MockBean
    TrackerFactory trackerFactory;

    /**
     * If method is OK. You must see this log
     *
     * 2022-06-19 19:19:35.883  INFO 70781 --- [           main] c.m.c.service.CourierLocationObserver    : Courier location under 100 meters for store Ataşehir MMM Migros - Distance : 1.1111357133499613
     */
    @Test
    void whenUpdateCourierLocation_withValidLocation_shouldLogNearlyStores(){

        Courier courier = new Courier()
                .setId(1L)
                .setLat(40.9923407)
                .setLng(29.1244229);

        Optional<Courier> getCourier = Optional.of(new Courier()
                .setId(1L)
                .setName("Guven")
                .setLat(40.9923407)
                .setLng(28.839881));

        CourierTracker courierTracker = new CourierTracker(getCourier.get(), distanceCalculator);
        courierTracker.addObserver(new CourierLocationObserver());

        Mockito.when(courierRepository.findById(courier.getId())).thenReturn(getCourier);
        Mockito.when(trackerFactory.getCourierTracker(courier.getId())).thenReturn(courierTracker);

        courierService.updateCourier(courier);

        assertTrue(true);

    }
}