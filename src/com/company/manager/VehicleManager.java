package com.company.manager;
import com.company.constants.BookingStatus;
import com.company.model.Booking;
import com.company.strategy.BookingStrategy;
import com.company.strategy.DefaultBookingStrategy;

import java.util.LinkedHashMap;
import java.util.Map;

public class VehicleManager {

    private static Map<String, Integer> vehiclePriceMap = new LinkedHashMap<>();
    private static final Object syncObj = new Object();
    private static VehicleManager INSTANCE = null;
    private static Map<String, BookingStatus> vehicleBookingStatusMap = new LinkedHashMap<>();

    public static VehicleManager getInstance() {
        if( INSTANCE == null) {
            synchronized (syncObj) {
                if (INSTANCE == null) {
                    INSTANCE = new VehicleManager();
                }
            }
        }
        return INSTANCE;
    }

    public Integer getVehiclePrice(String vehicleId) {
        return vehiclePriceMap.get(vehicleId);
    }

    public void setVehiclePrice(String vehicleId, int price) {
        vehiclePriceMap.put(vehicleId, price);
    }

    public void setBookingStatus(String vehicleId, BookingStatus bookingStatus) {
        vehicleBookingStatusMap.put(vehicleId, bookingStatus);
    }

    public BookingStrategy getBookingStrategy() {
        return new DefaultBookingStrategy();
    }

    public BookingStatus getBookingStatus(String vehicleId) {
        //System.out.println("vehicleId - " + vehicleId);
        //System.out.println("vehicleBookingMap - " + vehicleBookingStatusMap);
        return vehicleBookingStatusMap.get(vehicleId);
    }


}
