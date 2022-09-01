package com.company.controller;

import com.company.model.RentalBranch;
import com.company.model.Vehicle;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RentalBranchController {

    private static final List<RentalBranch> companyBranches = new ArrayList<>();
    private static final Map<String, List<Vehicle>> branchVehicleMap = new LinkedHashMap<>();
    private static final List<RentalBranch> nonActiveBranch = new ArrayList<>();
    private static final Map<Integer, Vehicle> idToVehicleMap = new LinkedHashMap<>();
    private static RentalBranchController INSTANCE = null;
    private static final Object syncObj = new Object();

    public static RentalBranchController getInstance() {
       if( INSTANCE == null) {
            synchronized (syncObj) {
                if (INSTANCE == null) {
                    INSTANCE = new RentalBranchController();
                }
            }
       }
        return INSTANCE;
    }

    public boolean addBranch(RentalBranch branch) {
        return companyBranches.add(branch);
    }

    public boolean removeBranch(RentalBranch branch) {
        companyBranches.remove(branch);
        return nonActiveBranch.add(branch);
    }

    public boolean addVehicleToBranch(String branchId, Vehicle vehicle) {
        if (branchVehicleMap.containsKey(branchId)) {
            branchVehicleMap.get(branchId).add(vehicle);
        } else {
            List<Vehicle> list = new ArrayList<>();
            list.add(vehicle);
            branchVehicleMap.put(branchId, list);
        }
        idToVehicleMap.put(vehicle.getVehicleId(), vehicle);
        return true;
    }

    public Vehicle getVehicleFromId(int vehicleId) {
        return idToVehicleMap.get(vehicleId);
    }

    public  List<Vehicle> getVehicleList(String branchId) {
//        System.out.println("branchId - " + branchId);
//        System.out.println("branchVehicleMap - " + branchVehicleMap.entrySet());
        return branchVehicleMap.get(branchId);
    }

    public List<RentalBranch> getBranchList() {
        return companyBranches;
    }
}
