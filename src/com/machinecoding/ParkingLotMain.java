package com.machinecoding;

import com.machinecoding.enums.DisplayType;
import com.machinecoding.enums.VehicleType;
import com.machinecoding.model.ParkingLot;
import com.machinecoding.services.ParkingLotService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ParkingLotMain {
    public static void main(String[] args) throws IOException {
        //System.out.println("Hello Vibhor");
        ParkingLotService parkingLotService = new ParkingLotService();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //String[] str = br.readLine().split(" ");
        //parkingLotService.createParkingLot(str[1], Integer.parseInt(str[2]), Integer.parseInt(str[3]));

        outerloop:
        while(true){
            String[] cmd = br.readLine().split(" ");
            switch (cmd[0]){
                case "create_parking_lot":
                    parkingLotService.createParkingLot(cmd[1], Integer.parseInt(cmd[2]), Integer.parseInt(cmd[3]));
                    break;
                case "park_vehicle":
                    parkingLotService.parkVehicle(VehicleType.valueOf(cmd[1]), cmd[2], cmd[3]);
                    break;
                case "unpark_vehicle":
                    parkingLotService.unParkVehicle(cmd[1]);
                    break;
                case "display":
                    parkingLotService.displayLot(DisplayType.valueOf(cmd[1]), VehicleType.valueOf(cmd[2]));
                    break;
                case "exit":
                    break outerloop;
            }
        }
    }
}
