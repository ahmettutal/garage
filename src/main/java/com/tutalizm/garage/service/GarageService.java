package com.tutalizm.garage.service;

import com.tutalizm.garage.entity.SlotModel;
import com.tutalizm.garage.entity.VehicleModel;
import com.tutalizm.garage.payload.LeaveResponse;
import com.tutalizm.garage.payload.ParkResponse;
import com.tutalizm.garage.payload.StatusResponse;
import com.tutalizm.garage.payload.VehicleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tutalizm.garage.entity.Status.ALLOCATED;
import static java.util.Objects.isNull;
import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
@Service
public class GarageService {

    private final SlotService slotService;
    private final VehicleService vehicleService;

    public GarageService(SlotService slotService, VehicleService vehicleService) {
        this.slotService = slotService;
        this.vehicleService = vehicleService;
    }

    public ParkResponse park(String plate, String colour, VehicleType vehicleType) {

        if (slotService.isPlateExist(plate)) {
            log.warn("Invalid Plate");
            return new ParkResponse(false, "Invalid Plate");
        }

        final int width = vehicleType.getWidth();
        final List<SlotModel> availableSlots = slotService.getAvailableSlots(width);

        if (isEmpty(availableSlots)) {
            log.warn("Garage is full");
            return new ParkResponse(false, "Garage is Full");
        }

        return park(plate, colour, vehicleType, availableSlots);
    }

    private ParkResponse park(String plate, String colour, VehicleType vehicleType, List<SlotModel> availableSlots) {

        VehicleModel vehicleModel = vehicleService.createVehicle(plate, colour, vehicleType);
        slotService.allocate(availableSlots, vehicleModel);

        return new ParkResponse(vehicleModel.getTicket());
    }

    public LeaveResponse leave(String ticket) {

        VehicleModel vehicleModel = vehicleService.getByTicket(ticket);

        if (isNull(vehicleModel) || !ALLOCATED.equals(vehicleModel.getStatus())) {
            log.warn("Invalid ticket");
            return new LeaveResponse("Invalid ticket");
        }

        slotService.release(vehicleModel);
        vehicleService.leave(vehicleModel);

        return new LeaveResponse(true, "Vehicle Released");
    }

    public StatusResponse status() {
        return slotService.status();
    }

}
