package com.tutalizm.garage.repository;

import com.tutalizm.garage.entity.VehicleModel;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends BaseRepository<VehicleModel> {

    VehicleModel findVehicleModelByPlate(String plate);

    VehicleModel findVehicleModelByTicket(String ticket);

}
