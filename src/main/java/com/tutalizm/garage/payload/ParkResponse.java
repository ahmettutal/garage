package com.tutalizm.garage.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ParkResponse extends BaseResponse {

    private String ticket;

    public ParkResponse(String ticket) {
        setTicket(ticket);
        super.setSuccess(true);
        super.setMessage("Allocated");
    }

    public ParkResponse(boolean success, String message) {
        super.setSuccess(success);
        super.setMessage(message);
    }

}
