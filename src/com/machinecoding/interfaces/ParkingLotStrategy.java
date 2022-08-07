package com.machinecoding.interfaces;

import com.machinecoding.model.ParkingLot;
import com.machinecoding.model.Vehicle;

public interface ParkingLotStrategy {
    String parkVehicleReturnTicket(ParkingLot parkingLot, Vehicle vehicle);
}
