package com.machinecoding.services;

import com.machinecoding.enums.DisplayType;
import com.machinecoding.enums.VehicleType;
import com.machinecoding.interfaces.ParkingLotStrategy;
import com.machinecoding.model.ParkingLot;
import com.machinecoding.model.Slot;
import com.machinecoding.model.Vehicle;

public class ParkingLotService {
    public ParkingLot parkingLot;
    public ParkingLotStrategy parkingLotStrategy;
    public void createParkingLot(String parkingLotId, Integer numOfFloors, Integer slotsPerFloor) {
        parkingLot = new ParkingLot(parkingLotId, numOfFloors, slotsPerFloor);
        System.out.println("Created parkingLot with " + "parkingLotId = " + parkingLotId + ", numOfFloors = " + numOfFloors + ", slotsPerFloor = " + slotsPerFloor);
        parkingLotStrategy = new NearestParkingLotStrategy();
        return;
    }
    public void displayUnoccupied() {
        System.out.println("UNOCCUPIED SLOTS:");
        for(int floorId = 0; floorId < parkingLot.getFloors(); floorId++) {
            for(Slot slot : parkingLot.getUnOccupiedSlots().get(floorId)) {
                System.out.println(slot.getSlotId() + " " + slot.getFloorId() + " " + slot.getOccupied() + " " + slot.getSlotType());
            }
        }
    }
    public void displayOccupied() {
        System.out.println("OCCUPIED SLOTS:");
        for(int floorId = 0; floorId < parkingLot.getFloors(); floorId++) {
            for(Slot slot : parkingLot.getOccupiedSlots().get(floorId)) {
                System.out.println(slot.getSlotId() + " " + slot.getFloorId() + " " + slot.getOccupied() + " " + slot.getSlotType());
            }
        }
    }
    public void displayLot(DisplayType displayType, VehicleType vehicleType) {
        for(int floorId = 0; floorId < parkingLot.getFloors(); floorId++) {
            Integer freeSlotsFloorWise = 0;
            if(displayType == DisplayType.free_count) {
                System.out.print("No. of free slots for " + vehicleType.toString() + " on Floor" + (floorId + 1) + ": ");
            }
            else if(displayType == DisplayType.free_slots){
                System.out.print("Free slots for " + vehicleType.toString() + " on Floor " + (floorId + 1) + ": ");
            }
            else {
                System.out.print("Occupied slots for " + vehicleType.toString() + " on Floor " + (floorId + 1) + ": ");
            }
            if(displayType == DisplayType.occupied_slots) {
                for (Slot slot : parkingLot.getOccupiedSlots().get(floorId)) {
                    if (slot.getSlotType() == vehicleType) {
                        freeSlotsFloorWise++;
                        if (displayType == DisplayType.occupied_slots) {
                            System.out.print(slot.getSlotId() + ",");
                        }
                    }
                }
            }
            else {
                for (Slot slot : parkingLot.getUnOccupiedSlots().get(floorId)) {
                    if (slot.getSlotType() == vehicleType) {
                        freeSlotsFloorWise++;
                        if (displayType == DisplayType.free_slots) {
                            System.out.print(slot.getSlotId() + ",");
                        }
                    }
                }
            }
            if(displayType.equals(DisplayType.free_count)) {
                System.out.print(freeSlotsFloorWise);
            }
            System.out.println();
        }
        return;
    }
    public void unParkVehicle(String ticket) {
        String[] str = ticket.split("_");
        String parkingLotId = str[0];
        String floor = str[1];
        String slotId = str[2];
        Integer floorIterator = Integer.parseInt(floor) - 1;
        String vehicleRegNo = null, vehicleColor = null;
        for(Slot slot : parkingLot.getOccupiedSlots().get(floorIterator)) {
            if(slot.getSlotId() == Integer.parseInt(slotId)) {
                Vehicle vehicleToUnPark = slot.getVehicle();
                vehicleColor = vehicleToUnPark.getColor();
                vehicleRegNo = vehicleToUnPark.getRegNo();
                Slot newlyVacatedSlot = new Slot(slot.slotType,false, slot.getSlotId(), slot.getFloorId());
                parkingLot.getUnOccupiedSlots().get(floorIterator).add(newlyVacatedSlot);
                parkingLot.getOccupiedSlots().get(floorIterator).remove(slot);
                Integer currUnoccupiedSlots = parkingLot.getTotalUnoccupiedSlots().get(vehicleToUnPark.getVehicleType());
                parkingLot.getTotalUnoccupiedSlots().put(vehicleToUnPark.getVehicleType(), currUnoccupiedSlots + 1);
                break;
            }
        }
        if(vehicleRegNo == null) {
            System.out.println("Invalid Ticket");
            return;
        }
        else {
            System.out.println("Unparked vehicle with reg no: " + vehicleRegNo + " and color: " + vehicleColor);
        }
        displayUnoccupied();
        displayOccupied();
        return;
    }
    public String parkVehicle(VehicleType vehicleType, String regNo, String color) {
        String ticket = "NA";
        Vehicle vehicle = new Vehicle(color, regNo, vehicleType);
        if(parkingLot.getTotalUnoccupiedSlots().get(vehicleType) <= 0) {
            System.out.println("Parking Lot Full");
            return ticket;
        }
        ticket = parkingLotStrategy.parkVehicleReturnTicket(parkingLot,vehicle);
        System.out.println("Parked Vehicle. Ticket id: " + ticket);
        displayUnoccupied();
        displayOccupied();
        return ticket;
    }
}
