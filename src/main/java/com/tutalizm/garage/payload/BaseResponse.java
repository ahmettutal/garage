package com.tutalizm.garage.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class BaseResponse {

    private boolean success = false;
    private String message;

}
