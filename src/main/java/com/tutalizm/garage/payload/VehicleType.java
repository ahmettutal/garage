package com.tutalizm.garage.payload;

public enum VehicleType {

    Car(1), Jeep(2), Truck(4);

    private final int width;

    VehicleType(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

}
