package com.migros.couriertracking.controller.v1.model.request;

import lombok.Data;

@Data
public class UpdateCourierRequest {

    private Long id;
    private String name;
    private Double lat;
    private Double lng;

}
