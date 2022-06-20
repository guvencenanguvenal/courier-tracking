package com.migros.couriertracking.controller.v1.api;

import com.migros.couriertracking.model.Response;
import com.migros.couriertracking.controller.v1.model.response.AllTravelDistanceResponse;
import com.migros.couriertracking.controller.v1.model.request.CreateCourierRequest;
import com.migros.couriertracking.controller.v1.model.request.UpdateCourierRequest;
import com.migros.couriertracking.model.Courier;
import com.migros.couriertracking.service.CourierService;
import com.migros.couriertracking.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courier")
public class CourierController {

    @Autowired
    private CourierService courierService;

    @Autowired
    private MapperUtil mapperUtil;

    @GetMapping
    public Response get(@RequestParam("id") Long id) {
        return Response.ok().setPayload(courierService.getCourier(id));
    }

    @GetMapping("/all")
    public Response getAll() {
        return Response.ok().setPayload(courierService.getAllCourier());
    }

    @GetMapping("/allTravelDistance")
    public Response getAllTravelDistance(@RequestParam("id") Long id) {
        return Response.ok().setPayload(new AllTravelDistanceResponse()
                .setMeters(courierService.getTotalTravelDistance(id)));
    }

    @PostMapping
    public Response add(@RequestBody CreateCourierRequest request){
        Courier courier = mapperUtil.map(request, Courier.class);
        return Response.ok().setPayload(courierService.addCourier(courier));
    }

    @PutMapping
    public Response update(@RequestBody UpdateCourierRequest request) {
        Courier courier = mapperUtil.map(request, Courier.class);
        return Response.ok().setPayload(courierService.updateCourier(courier));
    }

    @DeleteMapping
    public Response delete(@RequestParam("id") Long id) {
        courierService.deleteCourier(id);
        return Response.ok();
    }

}
