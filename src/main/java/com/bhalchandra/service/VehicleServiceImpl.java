package com.bhalchandra.service;

import com.bhalchandra.entity.Vehicle;
import com.bhalchandra.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    public VehicleRepository repository;


    @Override
    public List<Vehicle> findAll() {
        return (List<Vehicle>) repository.findAll();
    }

    @Override
    public Vehicle findOne(String id) {
        return null;
    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        return null;
    }

    @Override
    public Vehicle update(Vehicle vehicle) {
        return null;
    }
}
