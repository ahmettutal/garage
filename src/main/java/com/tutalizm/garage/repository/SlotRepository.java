package com.tutalizm.garage.repository;

import com.tutalizm.garage.entity.Status;
import com.tutalizm.garage.entity.SlotModel;
import com.tutalizm.garage.entity.VehicleModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepository extends BaseRepository<SlotModel> {

    List<SlotModel> findAllByStatusOrderBySlotNumber(Status status);

    List<SlotModel> findAllByVehicle(VehicleModel vehicleModel);

}
