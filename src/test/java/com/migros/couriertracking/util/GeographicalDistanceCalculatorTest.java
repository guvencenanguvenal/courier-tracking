package com.migros.couriertracking.util;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class GeographicalDistanceCalculatorTest {

    private final Double LAT1 = 40.9923407;
    private final Double LNG1 = 29.1244229;
    private final Double LAT2 = 40.9923307;
    private final Double LNG2 = 29.1244229;

    private final Double DISTANCE = 1.1111357133499613;

    @Autowired
    GeographicalDistanceCalculator geographicalDistanceCalculator;

    @Test
    void getDistanceBetweenTwoPoints() {
        Double distance = geographicalDistanceCalculator.getDistanceBetweenTwoPoints(LAT1, LNG1, LAT2, LNG2);
        assertEquals(DISTANCE, distance);
    }
}