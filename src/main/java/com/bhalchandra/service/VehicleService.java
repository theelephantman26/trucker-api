package com.bhalchandra.service;

import com.bhalchandra.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> findAll();

    Vehicle findOne(String id);

    Vehicle create(Vehicle vehicle);

    Vehicle update(Vehicle vehicle);
}
