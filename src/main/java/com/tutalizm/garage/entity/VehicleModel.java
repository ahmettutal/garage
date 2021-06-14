package com.tutalizm.garage.entity;

import com.tutalizm.garage.payload.VehicleType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@Table
@Entity
@EqualsAndHashCode(callSuper = true)
public class VehicleModel extends AuditEntity {

    private String plate;
    private String colour;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    private String ticket;

}
