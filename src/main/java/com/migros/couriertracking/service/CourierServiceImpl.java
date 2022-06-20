package com.migros.couriertracking.service;

import com.migros.couriertracking.exception.EntityNotFoundException;
import com.migros.couriertracking.model.Courier;
import com.migros.couriertracking.model.CourierLocationLog;
import com.migros.couriertracking.repository.CourierLocationLogRepository;
import com.migros.couriertracking.repository.CourierRepository;
import com.migros.couriertracking.util.DateUtil;
import com.migros.couriertracking.util.DistanceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierServiceImpl implements CourierService{

    private CourierRepository courierRepository;
    private CourierLocationLogRepository courierLocationLogRepository;
    private CourierLocationObserver courierLocationObserver;
    private DistanceCalculator distanceCalculator;
    private TrackerFactory trackerFactory;

    public CourierServiceImpl(CourierRepository courierRepository,
                              CourierLocationLogRepository courierLocationLogRepository,
                              CourierLocationObserver courierLocationObserver,
                              DistanceCalculator distanceCalculator,
                              TrackerFactory trackerFactory) {
        this.courierRepository = courierRepository;
        this.courierLocationLogRepository = courierLocationLogRepository;
        this.courierLocationObserver = courierLocationObserver;
        this.distanceCalculator = distanceCalculator;
        this.trackerFactory = trackerFactory;
    }

    @Override
    public Courier addCourier(Courier courier) {
        Courier savedCourier = courierRepository.save(courier);
        trackerFactory.createTracker(savedCourier);
        trackerFactory.getCourierTracker(savedCourier.getId()).addObserver(courierLocationObserver);

        return savedCourier;
    }

    @Override
    public void deleteCourier(Long id) {
        trackerFactory.deleteTracker(id);
        courierRepository.deleteById(id);
    }

    @Override
    public Courier updateCourier(Courier courier) {

        Courier tmp = courierRepository.findById(courier.getId())
                .orElseThrow(() -> new EntityNotFoundException("Courier not found"));

        if (courier.getName() != null)
            tmp.setName(courier.getName());
        tmp.setLat(courier.getLat());
        tmp.setLng(courier.getLng());

        CourierLocationLog courierLocationLog = new CourierLocationLog()
                .setCourierId(tmp.getId())
                .setLat(tmp.getLat())
                .setLng(tmp.getLng())
                .setLogDate(DateUtil.today());

        courierLocationLogRepository.save(courierLocationLog);

        trackerFactory.getCourierTracker(courier.getId()).updateLocation(courier.getLat(), courier.getLng());

        return courierRepository.save(tmp);
    }

    @Override
    public Courier getCourier(Long id) {
        return courierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Courier not found"));
    }

    @Override
    public List<Courier> getAllCourier() {
        return courierRepository.findAll();
    }

    @Override
    public List<CourierLocationLog> getAllCourierLocation(Long id) {
        return courierLocationLogRepository.findAllByCourierIdOrderByLogDateDesc(id);
    }

    @Override
    public Double getTotalTravelDistance(Long id){
        Double result = 0.0;

        List<CourierLocationLog> allCourierLocations = courierLocationLogRepository.findAllByCourierIdOrderByLogDateDesc(id);

        if (allCourierLocations.size() == 0) {
            throw new EntityNotFoundException("Courier log not found");
        }

        for (int i = 0; i < allCourierLocations.size() - 1; i++) {
            result += distanceCalculator.getDistanceBetweenTwoPoints(
                    allCourierLocations.get(i).getLat(),
                    allCourierLocations.get(i).getLng(),
                    allCourierLocations.get(i + 1).getLat(),
                    allCourierLocations.get(i + 1).getLng());
        }

        return result;
    }

}
