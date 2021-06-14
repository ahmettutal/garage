package com.tutalizm.garage.service;

import com.tutalizm.garage.entity.SlotModel;
import com.tutalizm.garage.entity.VehicleModel;
import com.tutalizm.garage.payload.StatusResponse;
import com.tutalizm.garage.payload.StatusResponseItem;
import com.tutalizm.garage.repository.SlotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.tutalizm.garage.entity.Status.ALLOCATED;
import static com.tutalizm.garage.entity.Status.AVAILABLE;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
@Service
public class SlotService {

    private final SlotRepository repository;

    public SlotService(SlotRepository repository) {
        this.repository = repository;
    }

    public boolean isPlateExist(String plate) {
        final List<SlotModel> allocatedSlots = repository.findAllByStatusOrderBySlotNumber(ALLOCATED);
        if (!isEmpty(allocatedSlots)) {
            for (SlotModel allocatedSlot : allocatedSlots) {
                final VehicleModel vehicle = allocatedSlot.getVehicle();
                if (vehicle.getPlate().equalsIgnoreCase(plate)) {
                    return true;
                }
            }
        }

        return false;
    }

    public List<SlotModel> getAvailableSlots(int width) {

        final List<SlotModel> activeSlots = repository.findAllByStatusOrderBySlotNumber(AVAILABLE);

        if (isEmpty(activeSlots) || activeSlots.size() < width) {
            return new ArrayList<>();
        }

        for (int i = 0; i < activeSlots.size() - width + 1; i++) {

            final List<SlotModel> availableSlots = new ArrayList<>();
            boolean isSerial = true;
            Integer tempSlotNumber = 0;

            for (int j = 0; j < width; j++) {
                final SlotModel slotModel = activeSlots.get(i + j);
                final Integer slotNumber = slotModel.getSlotNumber();

                if (tempSlotNumber != 0 && slotNumber - tempSlotNumber != 1) {
                    isSerial = false;
                    break;
                } else {
                    availableSlots.add(slotModel);
                }

                tempSlotNumber = slotNumber;
            }

            if (isSerial) {
                log.info("{} slot(s) found", availableSlots.size());
                return availableSlots;
            }
        }

        return new ArrayList<>();
    }

    public void allocate(List<SlotModel> availableSlots, VehicleModel vehicleModel) {

        availableSlots.forEach(availableSlot -> availableSlot.park(vehicleModel));

        repository.saveAll(availableSlots);

        log.info("Allocated {} slot(s)", availableSlots.size());
    }

    public void release(VehicleModel vehicleModel) {

        final List<SlotModel> allByVehicle = repository.findAllByVehicle(vehicleModel);

        allByVehicle.forEach(SlotModel::leave);

        repository.saveAll(allByVehicle);

        log.info("Released {} slot(s)", allByVehicle.size());
    }

    public StatusResponse status() {

        final List<SlotModel> allocatedSlots = repository.findAllByStatusOrderBySlotNumber(ALLOCATED);

        if (isEmpty(allocatedSlots)) {
            log.info("Garage is Empty");
            return new StatusResponse("Garage is Empty");
        }

        List<StatusResponseItem> items = new ArrayList<>();

        final Set<VehicleModel> vehicleModelSet = allocatedSlots.stream().map(SlotModel::getVehicle).collect(toSet());

        for (VehicleModel vehicleModel : vehicleModelSet) {

            final String plate = vehicleModel.getPlate();
            final String colour = vehicleModel.getColour();

            StatusResponseItem item = StatusResponseItem.builder()
                    .plate(plate)
                    .colour(colour)
                    .vehicleType(vehicleModel.getVehicleType())
                    .build();

            item.setSlots(
                    allocatedSlots
                            .stream().filter(s -> s.getVehicle().equals(vehicleModel)).collect(toSet())
                            .stream().map(SlotModel::getSlotNumber).collect(toList()));

            items.add(item);
        }

        return new StatusResponse(items);
    }

}
