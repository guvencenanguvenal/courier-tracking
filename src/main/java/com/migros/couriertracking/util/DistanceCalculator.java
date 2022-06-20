package com.migros.couriertracking.util;

public interface DistanceCalculator {

    Double getDistanceBetweenTwoPoints(final Double lat1, final Double lng1, final Double lat2, final Double lng2);

}
