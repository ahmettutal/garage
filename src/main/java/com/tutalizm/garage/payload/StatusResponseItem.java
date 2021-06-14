package com.tutalizm.garage.payload;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StatusResponseItem {

    private String plate;
    private String colour;
    private VehicleType vehicleType;
    private List<Integer> slots;

}
