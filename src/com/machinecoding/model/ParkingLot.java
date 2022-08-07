package com.machinecoding.model;

import com.machinecoding.enums.VehicleType;
import lombok.Data;

import java.util.*;

class CustomComp implements Comparator<Slot> {

    // Method
    // To compare two strings
    public int compare(Slot slot1, Slot slot2)
    {
        return slot1.getSlotId().compareTo(slot2.getSlotId());
    }
}

@Data
public class ParkingLot {
    public String parkingLotid;
    public Integer floors;
    public HashMap<VehicleType, Integer> totalUnoccupiedSlots;
    public ParkingLot(String parkingLotid, Integer floors, Integer slotsPerFloor) {
        this.parkingLotid = parkingLotid;
        this.floors = floors;
        this.slotsPerFloor = slotsPerFloor;
        totalUnoccupiedSlots = new HashMap<>();
        initializeUnOccupiedSlots();
    }

    public Integer slotsPerFloor;
    public final int reservedForTruck = 1;
    public final int reservedForBike = 2;
    /*public ArrayList<TreeSet<Integer>> unOccupiedSlots;

    public void initializeUnOccupiedSlots() {
        unOccupiedSlots = new ArrayList<>();
        for(int i=0;i<floors;i++) {
            TreeSet<Integer> ts = new TreeSet<>();
            for (int j = 1; j <= slotsPerFloor; j++) {
                ts.add(j);
            }
            unOccupiedSlots.add(ts);
        }
    }*/
    public ArrayList<TreeSet<Slot>> unOccupiedSlots;
    public ArrayList<TreeSet<Slot>> occupiedSlots;
    public void initializeUnOccupiedSlots() {
        Integer totalSlots = floors * slotsPerFloor;
        totalUnoccupiedSlots.put(VehicleType.TRUCK, reservedForTruck * floors);
        totalUnoccupiedSlots.put(VehicleType.BIKE, reservedForBike * floors);
        totalUnoccupiedSlots.put(VehicleType.CAR, totalSlots - floors* (reservedForTruck + reservedForBike));
        unOccupiedSlots = new ArrayList<>();
        occupiedSlots = new ArrayList<>();
        for(int floorNum = 1; floorNum <= floors; floorNum++) {
            TreeSet<Slot> ts = new TreeSet<>(new CustomComp());
            int slotNo;
            for (slotNo = 1; slotNo <= reservedForTruck; slotNo++) {
                ts.add(new Slot(VehicleType.TRUCK,false, slotNo, floorNum));
            }
            for (; slotNo <= reservedForBike + reservedForTruck; slotNo++) {
                ts.add(new Slot(VehicleType.BIKE,false, slotNo, floorNum));
            }
            for (; slotNo <= slotsPerFloor; slotNo++) {
                ts.add(new Slot(VehicleType.CAR,false, slotNo, floorNum));
            }
            unOccupiedSlots.add(ts);
            occupiedSlots.add(new TreeSet<>(new CustomComp()));
        }
    }

}
