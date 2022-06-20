package com.migros.couriertracking.repository;

import com.migros.couriertracking.model.CourierLocationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourierLocationLogRepository extends JpaRepository<CourierLocationLog, Long> {

    public List<CourierLocationLog> findAllByCourierIdOrderByLogDateDesc(Long courierId);

}
