package com.migros.couriertracking.controller.v1.model.request;

import lombok.Data;

@Data
public class CreateCourierRequest {

    private String name;
    private Double lat;
    private Double lng;

}
