package com.company.strategy;

import com.company.constants.VehicleType;

import java.util.Date;

public interface BookingStrategy {

    Integer bookVehicle(String branchId, String vehicleType, int startTime, int endTime);
}
