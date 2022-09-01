package com.company.model;

import com.company.constants.Color;

public class Vehicle {

    int vehicleId;
    String vehicleNo;
    Color vehicleColor;
    String vehicleName;
    String vehicleType;
    String owner;
    Integer kmDriven;
    static Integer vecId = 1567;



    private Vehicle() {
        setVehicleId();
    }

    public int getVehicleId() {
        return vehicleId;
    }

    private void setVehicleId() {
        vehicleId = vecId++;
    }

    void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    void setVehicleColor(Color vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setKmDriven(Integer kmDriven) {
        this.kmDriven = kmDriven;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public Color getVehicleColor() {
        return vehicleColor;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getOwner() {
        return owner;
    }

    public Integer getKmDriven() {
        return kmDriven;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        Vehicle vehicle;

        public Builder() {
            vehicle = new Vehicle();
        }

        private Builder self() {
            return this;
        }

        public Builder setVehicleNo(String number) {
            vehicle.setVehicleNo(number);
            return self();
        }

        public Builder setVehicleName(String name) {
            vehicle.setVehicleName(name);
            return self();
        }

        public Builder setVehicleColor(Color color) {
            vehicle.setVehicleColor(color);
            return self();
        }

        public Builder setVehicleType(String type) {
            vehicle.setVehicleType(type);
            return self();
        }

        public Builder setBikeOwner(String owner) {
            vehicle.setOwner(owner);
            return self();
        }

        public Builder setKMDriven(int totalKM) {
            vehicle.setKmDriven(totalKM);
            return self();
        }

        public Vehicle build() {
            return vehicle;
        }
    }
}
