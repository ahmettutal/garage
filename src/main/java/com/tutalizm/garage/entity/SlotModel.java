package com.tutalizm.garage.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static com.tutalizm.garage.entity.Status.ALLOCATED;
import static com.tutalizm.garage.entity.Status.AVAILABLE;

@Data
@Table
@Entity
@EqualsAndHashCode(callSuper = true)
public class SlotModel extends AuditEntity {

    @ManyToOne
    private GarageModel garage;

    @ManyToOne
    private VehicleModel vehicle;

    private Integer slotNumber;

    public void park(VehicleModel vehicleModel) {
        setVehicle(vehicleModel);
        setStatus(ALLOCATED);
    }

    public void leave() {
        setVehicle(null);
        setStatus(AVAILABLE);
    }

}
