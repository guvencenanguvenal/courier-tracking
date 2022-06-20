package com.migros.couriertracking.service;

import com.migros.couriertracking.model.Courier;
import com.migros.couriertracking.repository.CourierLocationLogRepository;
import com.migros.couriertracking.repository.CourierRepository;
import com.migros.couriertracking.util.DistanceCalculator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class CourierServiceImplTest {

    @Autowired
    CourierServiceImpl courierService;

    @MockBean
    CourierRepository courierRepository;

    @Test
    void whenAddCourier_withValidCourier_shouldReturnSavedCourier() {
        Courier courier = new Courier()
                .setName("Guven")
                .setLat(40.9923407)
                .setLng(28.839881);

        Courier savedCourier = new Courier()
                .setId(1L)
                .setName("Guven")
                .setLat(40.9923407)
                .setLng(28.839881);

        Mockito.when(courierRepository.save(courier)).thenReturn(savedCourier);

        Courier serviceResult = courierService.addCourier(courier);

        assertEquals(courier.getName(), serviceResult.getName());
        assertEquals(courier.getLat(), serviceResult.getLat());
        assertEquals(courier.getLng(), serviceResult.getLng());

    }

    @Test
    void whenUpdateCourier_withValidCourier_shouldReturnUpdatedCourier() {

        Courier courier = new Courier()
                .setId(1L)
                .setName("Guven")
                .setLat(40.9923407)
                .setLng(28.839881);

        Courier updatedCourier = new Courier()
                .setId(1L)
                .setName("Guven")
                .setLat(40.9923407)
                .setLng(28.839881);

        Mockito.when(courierRepository.save(courier)).thenReturn(updatedCourier);

        Courier serviceResult = courierService.addCourier(courier);

        assertEquals(courier.getName(), serviceResult.getName());
        assertEquals(courier.getLat(), serviceResult.getLat());
        assertEquals(courier.getLng(), serviceResult.getLng());

    }

    @Test
    void whenUpdateCourier_withoutNameCourier_shouldReturnUpdatedCourier() {

        Courier courier = new Courier()
                .setLat(40.9923407)
                .setLng(28.839881);

        Courier updatedCourier = new Courier()
                .setId(1L)
                .setName("Guven")
                .setLat(40.9923407)
                .setLng(28.839881);

        Mockito.when(courierRepository.save(courier)).thenReturn(updatedCourier);

        Courier serviceResult = courierService.addCourier(courier);

        assertEquals(courier.getLat(), serviceResult.getLat());
        assertEquals(courier.getLng(), serviceResult.getLng());

    }

    @Test
    void whenGetCourier_withValidId_shouldReturnNonEmptyCourier() {

        Long courierId = 1L;

        Optional<Courier> getCourier = Optional.of(new Courier()
                .setId(1L)
                .setName("Guven")
                .setLat(40.9923407)
                .setLng(28.839881));

        Mockito.when(courierRepository.findById(courierId)).thenReturn(getCourier);

        assertFalse(getCourier.isEmpty());
    }

    @Test
    void whenGetCourier_withInvalidId_shouldReturnEmpty() {

        Long courierId = 0L;

        Optional<Courier> getCourier = Optional.empty();

        Mockito.when(courierRepository.findById(courierId)).thenReturn(getCourier);

        assertTrue(getCourier.isEmpty());
    }

    @Test
    void whenGetAllCourier_shouldReturnAllCourier() {

        List<Courier> allCourier = new ArrayList<>();
        allCourier.add(new Courier()
                .setId(1L)
                .setName("Guven")
                .setLat(40.9923407)
                .setLng(28.839881));

        allCourier.add(new Courier()
                .setId(2L)
                .setName("Ali")
                .setLat(40.9923407)
                .setLng(28.839881));

        Mockito.when(courierRepository.findAll()).thenReturn(allCourier);

        List<Courier> serviceResult = courierService.getAllCourier();

        assertEquals(allCourier.size(), serviceResult.size());

    }



}