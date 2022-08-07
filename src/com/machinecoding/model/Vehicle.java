package com.machinecoding.model;

import com.machinecoding.enums.VehicleType;
import lombok.Data;

@Data
public class Vehicle {
    public String color;
    public String regNo;
    public VehicleType vehicleType;

    public Vehicle(String color, String regNo, VehicleType vehicleType) {
        this.color = color;
        this.regNo = regNo;
        this.vehicleType = vehicleType;
    }
}
