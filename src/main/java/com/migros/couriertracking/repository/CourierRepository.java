package com.migros.couriertracking.repository;

import com.migros.couriertracking.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {

    @Override
    Optional<Courier> findById(Long aLong);

    @Override
    List<Courier> findAll();
}
