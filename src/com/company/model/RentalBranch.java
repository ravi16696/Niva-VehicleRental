package com.company.model;

import com.company.constants.VehicleType;

import java.util.HashSet;

public class RentalBranch extends CompanyBranch{

    private HashSet<String> vehicleTypes;

    public RentalBranch(String branchName) {
        super(branchName);
        vehicleTypes = new HashSet<>();
    }

    public void setVehicleTypes(String type) {
        vehicleTypes.add(type);
    }

    public HashSet<String> getVehicleTypes() {
        return vehicleTypes;
    }
}
