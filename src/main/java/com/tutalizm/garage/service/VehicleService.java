package com.tutalizm.garage.service;

import com.tutalizm.garage.entity.VehicleModel;
import com.tutalizm.garage.payload.VehicleType;
import com.tutalizm.garage.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.tutalizm.garage.entity.Status.ALLOCATED;
import static com.tutalizm.garage.entity.Status.LEFT;
import static java.util.Objects.isNull;

@Slf4j
@Service
public class VehicleService {

    private final VehicleRepository repository;

    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public VehicleModel createVehicle(String plate, String colour, VehicleType vehicleType) {

        final String ticket = UUID.randomUUID().toString();

        VehicleModel vehicleModel = repository.findVehicleModelByPlate(plate);

        if (!isNull(vehicleModel)) {

            vehicleModel.setTicket(ticket);
            vehicleModel.setStatus(ALLOCATED);

            return repository.save(vehicleModel);
        }

        vehicleModel = new VehicleModel();
        vehicleModel.setPlate(plate);
        vehicleModel.setColour(colour);
        vehicleModel.setVehicleType(vehicleType);
        vehicleModel.setTicket(ticket);
        vehicleModel.setStatus(ALLOCATED);

        return repository.save(vehicleModel);
    }

    public VehicleModel getByTicket(String ticket) {
        return repository.findVehicleModelByTicket(ticket);
    }

    public void leave(VehicleModel vehicleModel) {

        vehicleModel.setStatus(LEFT);

        repository.save(vehicleModel);
    }
}
