package com.company.activity;

import com.company.constants.BookingStatus;
import com.company.controller.RentalBranchController;
import com.company.manager.VehicleManager;
import com.company.model.RentalBranch;
import com.company.model.Vehicle;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RentalBranchActivity {

    RentalBranchController rentalBranchController;

    public RentalBranchActivity() {
        rentalBranchController = RentalBranchController.getInstance();
    }

    public boolean onBoardBranch(RentalBranch branch) {
        return rentalBranchController.addBranch(branch);
    }

    public boolean removeBranch(RentalBranch branch) {
        return rentalBranchController.removeBranch(branch);

    }

    public boolean addVehicleToBranch(RentalBranch branch, Vehicle vehicle, int price) {
        if (!branch.getVehicleTypes().contains(vehicle.getVehicleType())) {
            return false;
        }
        rentalBranchController.addVehicleToBranch(branch.getBranchName(), vehicle);
        VehicleManager.getInstance().setVehiclePrice(vehicle.getVehicleNo(), price);
        VehicleManager.getInstance().setBookingStatus(vehicle.getVehicleNo(), BookingStatus.AVAILABLE);
        return true;
    }

    public Integer bookVehicle(String branchId, String vehicleType, int start, int endTime) {
        return VehicleManager.getInstance().getBookingStrategy().bookVehicle(branchId,vehicleType,start, endTime);
    }

    public void displayAllVehicleForBranchSortedByPrice(String branchName, int start, int end) {
        RentalBranch branch = getBranchFromName(branchName);
        List<Vehicle> vehicleList = rentalBranchController.getVehicleList(branch.getBranchName());

        //System.out.println("Displaying all vehicles for branch - " + branch.getBranchName());

        if(vehicleList == null) {
            System.out.println("vehicleList is 0");
            return;
        }
        List<Pair<String, Integer>> vehiclePriceSortList = new ArrayList<>();
        for (int i=0; i< vehicleList.size(); i++) {
            if (VehicleManager.getInstance().getBookingStatus(vehicleList.get(i).getVehicleNo()) != BookingStatus.AVAILABLE) {
                continue;
            }
            String vehicleName = vehicleList.get(i).getVehicleNo();
            //System.out.println("vehicleName - " + vehicleName);
            Integer price = VehicleManager.getInstance().getVehiclePrice(vehicleName);
            //System.out.println("price - " + price);
            if(price != -1) {
                price = price * (end -start);
            }
            vehiclePriceSortList.add(new Pair<>(vehicleName, price));
        }
        vehiclePriceSortList.sort(new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                return 0;
            }
        });

        for (int i=0; i<vehiclePriceSortList.size(); i++) {
            System.out.print(vehiclePriceSortList.get(i).getKey());
            if(i!=vehiclePriceSortList.size()-1) {
                System.out.print(",");
            }
        }
    }


    public void displayAllBranch() {
        List<RentalBranch> branchList = rentalBranchController.getBranchList();
        System.out.println("Displaying all branches");
        for (int i=0; i<branchList.size(); i++) {
            System.out.println(branchList.get(i).getBranchName());
        }

    }

    public RentalBranch getBranchFromName(String branchName) {
        List<RentalBranch> branchList = rentalBranchController.getBranchList();

        for (int i =0; i<branchList.size(); i++) {
            if(branchList.get(i).getBranchName().equals(branchName)) {
                return branchList.get(i);
            }
        }
        return null;
    }

}
