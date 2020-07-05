package com.wolve.uberclone.models;

public class Driver {
    String Id;
    String User;
    String Email;
    String VehicleBrand;
    String VehiclePlate;

    public Driver(){}

    public Driver(String id, String user, String email, String vehicleBrand, String vehiclePlate) {
        Id = id;
        User = user;
        Email = email;
        VehicleBrand = vehicleBrand;
        VehiclePlate = vehiclePlate;
    }
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getVehicleBrand() {
        return VehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        VehicleBrand = vehicleBrand;
    }

    public String getVehiclePlate() {
        return VehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        VehiclePlate = vehiclePlate;
    }
}
