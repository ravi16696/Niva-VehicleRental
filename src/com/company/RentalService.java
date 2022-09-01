package com.company;

import com.company.activity.RentalBranchActivity;
import com.company.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RentalService {

    public static void main(String[] args) {

        //readInputFromFile("/Users/kmarycw/Documents/testFile.txt");
        Scanner scanner = new Scanner(System.in);
        String fileLocation = scanner.nextLine();
        readInputFromFile(fileLocation);

    }

    public static void readInputFromFile(String fileLocation) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileLocation));
            String line = reader.readLine();
            while (line != null) {
                //System.out.println(line);
                // read next line
                executeCommand(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void executeCommand(String line) {
        String[] splitStr = line.split("\\s+");
        String command = splitStr[0];
        //System.out.println("Command - " + command);
        RentalBranchActivity branchActivity = new RentalBranchActivity();
        if (command.equals("ADD_BRANCH")) {
            String branchName = splitStr[1];
            RentalBranch branch = new RentalBranch(branchName);
            Boolean b = branchActivity.onBoardBranch(branch);
            printBoolean(b);
            String typeList = splitStr[2];
            if (typeList.contains(",")) {
                String[] typeListSplit = typeList.split("[,]", 0);
                for (int i=0; i<typeListSplit.length; i++) {
                    branch.setVehicleTypes(typeListSplit[i]);
                }
            }
            //branchActivity.displayAllBranch();
        } else if (command.equals("ADD_VEHICLE")) {
            String branchName = splitStr[1];
            String vehicleType = splitStr[2];
            String vehicleId = splitStr[3];
            int price = Integer.parseInt(splitStr[4]);
            RentalBranch branch = branchActivity.getBranchFromName(branchName);
            Vehicle vehicle = Vehicle.builder().setVehicleNo(vehicleId).setVehicleType(vehicleType).build();
            printBoolean(branchActivity.addVehicleToBranch(branch, vehicle, price));
           // branchActivity.displayAllVehicleForBranch(branchName);
        } else if (command.equals("BOOK")) {
            String branchId = splitStr[1];
            String vehicleType = splitStr[2];
            int start = Integer.parseInt(splitStr[3]);
            int end = Integer.parseInt(splitStr[4]);
            int totalPrice = branchActivity.bookVehicle(branchId, vehicleType, start, end);
            System.out.println(totalPrice);
        } else if (command.equals("DISPLAY_VEHICLES")) {
            String branchName = splitStr[1];
            int start = Integer.parseInt(splitStr[2]);
            int end = Integer.parseInt(splitStr[3]);
            branchActivity.displayAllVehicleForBranchSortedByPrice(branchName, start, end);
        }
    }

    public static void printBoolean(Boolean b) {
        if (b) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }
    }

}
