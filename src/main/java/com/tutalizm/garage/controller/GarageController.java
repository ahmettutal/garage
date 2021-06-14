package com.tutalizm.garage.controller;

import com.tutalizm.garage.payload.LeaveResponse;
import com.tutalizm.garage.payload.ParkResponse;
import com.tutalizm.garage.payload.StatusResponse;
import com.tutalizm.garage.payload.VehicleType;
import com.tutalizm.garage.service.GarageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GarageController {

    private final GarageService service;

    public GarageController(GarageService service) {
        this.service = service;
    }

    @PostMapping("/park")
    public ResponseEntity<ParkResponse> park(@RequestParam String plate,
                                             @RequestParam String colour,
                                             @RequestParam VehicleType vehicleType) {
        return ResponseEntity.ok(service.park(plate, colour, vehicleType));
    }

    @PostMapping("/leave/{ticket}")
    public ResponseEntity<LeaveResponse> leave(@PathVariable String ticket) {
        return ResponseEntity.ok(service.leave(ticket));
    }

    @GetMapping("/status")
    public ResponseEntity<StatusResponse> status() {
        return ResponseEntity.ok(service.status());
    }

}
