package com.bhalchandra.service;

import com.bhalchandra.entity.Vehicle;
import com.bhalchandra.exception.BadRequestException;
import com.bhalchandra.exception.ResourceNotFoundException;
import com.bhalchandra.repository.VehicleRepository;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    public VehicleRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> findAll() {
        return (List<Vehicle>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Vehicle findOne(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle Not Found"));
    }

    @Override
    @Transactional
    public List<Vehicle> createElseUpdate(List<Vehicle> vehicles) {
        vehicles = (ArrayList<Vehicle>) vehicles;
        vehicles.forEach((vehicle -> repository.save(vehicle)));
        return (List<Vehicle>) vehicles;
    }

}
