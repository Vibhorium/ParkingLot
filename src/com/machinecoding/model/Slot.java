package com.machinecoding.model;

import com.machinecoding.enums.VehicleType;

public class Slot {
    public VehicleType slotType;
    public Boolean isOccupied;
    public Integer slotId;
    public Integer floorId;
    public Vehicle vehicle;
    public Slot(VehicleType slotType, Boolean isOccupied, Integer slotId, Integer floorId) {
        this.slotType = slotType;
        this.isOccupied = isOccupied;
        this.slotId = slotId;
        this.floorId = floorId;
    }

    public VehicleType getSlotType() {
        return slotType;
    }

    public void setSlotType(VehicleType slotType) {
        this.slotType = slotType;
    }

    public Boolean getOccupied() {
        return isOccupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }
}
