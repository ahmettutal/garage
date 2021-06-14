package com.tutalizm.garage.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LeaveResponse extends BaseResponse {

    public LeaveResponse(String message) {
        super.setMessage(message);
    }

    public LeaveResponse(boolean success, String message) {
        super.setSuccess(success);
        super.setMessage(message);
    }

}
