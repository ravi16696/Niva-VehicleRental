package com.company.model;

import com.company.constants.BookingStatus;
import com.company.constants.VehicleType;

import java.util.Date;

public class Booking {

    Integer bookingId;
    String vehicleId;
    int startTime;
    int endTime;
    String vehicleType;
    String bookingName;
    Integer bookingPrice;
    BookingStatus bookingStatus;

    private static int idCounter = 232452;

    public Booking() {
        bookingId = getBookingId();
    }

    public Integer getBookingId() {
        return idCounter++;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    public Integer getBookingPrice() {
        return bookingPrice;
    }

    public void setBookingPrice(Integer bookingPrice) {
        this.bookingPrice = bookingPrice;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
