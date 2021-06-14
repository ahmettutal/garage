package com.tutalizm.garage.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class StatusResponse extends BaseResponse {

    private List<StatusResponseItem> vehicles;

    public StatusResponse(List<StatusResponseItem> vehicles) {
        setVehicles(vehicles);
        super.setSuccess(true);
    }

    public StatusResponse(String message) {
        super.setSuccess(true);
        super.setMessage(message);
    }

}
