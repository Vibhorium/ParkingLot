package com.machinecoding.services;

import com.machinecoding.interfaces.ParkingLotStrategy;
import com.machinecoding.model.ParkingLot;
import com.machinecoding.model.Slot;
import com.machinecoding.model.Vehicle;

public class NearestParkingLotStrategy implements ParkingLotStrategy {
    @Override
    public String parkVehicleReturnTicket(ParkingLot parkingLot, Vehicle vehicle) {
        String ticketId = parkingLot.getParkingLotid() + "_";
        for(int floorId = 0; floorId < parkingLot.getFloors(); floorId++) {
            for(Slot slot : parkingLot.getUnOccupiedSlots().get(floorId)) {
                if(!slot.getOccupied() && slot.getSlotType() == vehicle.getVehicleType()) {
                    String unOccupiedFloorId = String.valueOf(slot.getFloorId());
                    String unOccupiedSlotId = String.valueOf(slot.getSlotId());
                    Slot newlyOccupiedSlot = new Slot(slot.getSlotType(),true, slot.getSlotId(), slot.getFloorId());
                    newlyOccupiedSlot.setVehicle(vehicle);
                    parkingLot.getOccupiedSlots().get(floorId).add(newlyOccupiedSlot);
                    parkingLot.getUnOccupiedSlots().get(floorId).remove(slot);
                    Integer currUnoccupiedSlots = parkingLot.getTotalUnoccupiedSlots().get(vehicle.getVehicleType());
                    parkingLot.getTotalUnoccupiedSlots().put(vehicle.getVehicleType(), currUnoccupiedSlots - 1);
                    ticketId = ticketId + unOccupiedFloorId + "_" + unOccupiedSlotId;
                    return ticketId;
                }
            }
        }
        return ticketId;
    }
}
