package com.migros.couriertracking.controller.v1.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.migros.couriertracking.model.Response;
import com.migros.couriertracking.controller.v1.model.response.AllTravelDistanceResponse;
import com.migros.couriertracking.model.Courier;
import com.migros.couriertracking.service.CourierService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class CourierControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CourierService courierService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void whenGet_withValidId_shouldReturnCourier() throws Exception {

        Courier returnCourier = new Courier()
                .setId(1L)
                .setName("Courier 1")
                .setLat(1.0)
                .setLng(1.0);

        when(courierService.getCourier(1L)).thenReturn(returnCourier);

        this.mockMvc.perform(get("/api/v1/courier?id=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Response.ok().setPayload(returnCourier))));
    }

    @Test
    void whenGetAll_withValidId_shouldReturnAllCouriers() throws Exception {

        List<Courier> returnCourier = new ArrayList<>();
          returnCourier.add(new Courier()
                .setId(1L)
                .setName("Courier 1")
                .setLat(1.0)
                .setLng(1.0));

        when(courierService.getAllCourier()).thenReturn(returnCourier);

        this.mockMvc.perform(get("/api/v1/courier/all")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Response.ok().setPayload(returnCourier))));

    }

    @Test
    void getAllTravelDistance() throws Exception {

        AllTravelDistanceResponse response = new AllTravelDistanceResponse()
                .setMeters(1.1);

        when(courierService.getTotalTravelDistance(1L)).thenReturn(1.1);

        this.mockMvc.perform(get("/api/v1/courier/allTravelDistance?id=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Response.ok().setPayload(response))));

    }

}