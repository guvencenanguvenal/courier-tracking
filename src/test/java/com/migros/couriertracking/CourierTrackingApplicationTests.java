package com.migros.couriertracking;

import com.migros.couriertracking.config.ModelMapperConfig;
import com.migros.couriertracking.config.ObjectMapperConfig;
import com.migros.couriertracking.config.StoreFileLoader;
import com.migros.couriertracking.controller.v1.api.CourierController;
import com.migros.couriertracking.repository.CourierLocationLogRepository;
import com.migros.couriertracking.repository.CourierRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CourierTrackingApplicationTests {

	@Autowired
	private CourierController courierController;

	@Autowired
	private ModelMapperConfig modelMapperConfig;

	@Autowired
	private ObjectMapperConfig objectMapperConfig;

	@Autowired
	private StoreFileLoader storeFileLoader;

	@Autowired
	private CourierLocationLogRepository courierLocationLogRepository;

	@Autowired
	private CourierRepository courierRepository;

	@Test
	void contextLoads() {
		assertThat(courierController).isNotNull();
		assertThat(modelMapperConfig).isNotNull();
		assertThat(objectMapperConfig).isNotNull();
		assertThat(storeFileLoader).isNotNull();
		assertThat(courierLocationLogRepository).isNotNull();
		assertThat(courierRepository).isNotNull();
	}

}
