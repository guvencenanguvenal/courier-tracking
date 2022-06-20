package com.migros.couriertracking.controller.v1.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class AllTravelDistanceResponse {

    private Double meters;

}
