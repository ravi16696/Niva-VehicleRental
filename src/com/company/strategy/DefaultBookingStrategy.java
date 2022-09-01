package com.company.strategy;

import com.company.constants.BookingStatus;
import com.company.constants.VehicleType;
import com.company.controller.RentalBranchController;
import com.company.manager.VehicleManager;
import com.company.model.Booking;
import com.company.model.Vehicle;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DefaultBookingStrategy implements BookingStrategy{


    @Override
    public Integer bookVehicle(String branchId, String vehicleType, int startTime, int endTime) {
        int minPriceVehicle =  bookMinimumPriceVehicle(branchId, vehicleType, startTime, endTime);
        return calculateTotalPrice(startTime, endTime, minPriceVehicle);
    }


    public Integer bookMinimumPriceVehicle(String branchId, String vehicleType, int startTime, int endTime) {

        String vehicleId = null;
        Integer minPrice = Integer.MAX_VALUE;

        List<Vehicle> vehicleList = RentalBranchController.getInstance().getVehicleList(branchId);

        if (vehicleList == null) {
            System.out.println("No vehicles available");
            return -1;
        }

        for (int i=0; i<vehicleList.size(); i++) {
            Vehicle vehicle = vehicleList.get(i);

            int price = VehicleManager.getInstance().getVehiclePrice(vehicle.getVehicleNo());
            if (vehicle.getVehicleType().equals(vehicleType) && minPrice > price
                    && VehicleManager.getInstance().getBookingStatus(vehicle.getVehicleNo()) != BookingStatus.BOOKED) {
                minPrice = price;
                vehicleId = vehicle.getVehicleNo();
            }

        }

        if (vehicleId != null) {
            Booking booking = doVehicleBooking(vehicleId, vehicleType, minPrice, startTime, endTime);
            VehicleManager.getInstance().setBookingStatus(vehicleId, BookingStatus.BOOKED);
            return minPrice;
        } else {
            return -1;
        }
    }

    public Booking doVehicleBooking(String vehicleId, String vehicleType, Integer price, int startTime, int endTime) {
        Booking booking = new Booking();
        booking.setBookingName("customer Name");
        booking.setVehicleType(vehicleType);
        booking.setVehicleId(vehicleId);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setBookingStatus(BookingStatus.BOOKED);
        return booking;
    }

    public Integer calculateTotalPrice(int start, int end, Integer price) {
        //will write the logic to get the price
        if(price < 0) {
            return -1;
        }
       // System.out.println("minimum price for vehicle - " + price);
        return (end - start) * price;
    }


}
