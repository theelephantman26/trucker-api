package com.bhalchandra.service;

import com.bhalchandra.entity.Alert;
import com.bhalchandra.entity.Reading;
import com.bhalchandra.entity.Tires;
import com.bhalchandra.entity.Vehicle;
import com.bhalchandra.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlertServiceImpl implements AlertService {
    @Autowired
    AlertRepository repository;

    @Autowired
    VehicleService vehicleService;

    @Override
    @Transactional(readOnly = true)
    public List<Alert> findAll() {
        return (List<Alert>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public Alert create(Alert alert) {
        return repository.save(alert);
    }

    @Override
    @Async
    @Transactional
    public void checkAndCreateAlerts(Reading reading) {
        Vehicle vehicle = vehicleService.findOne(reading.getVin());
        if (reading.getEngineRpm() > vehicle.getRedlineRpm()) {
            Alert alert = new Alert();
            alert.setLevel("HIGH");
            alert.setType("ENGINE RPM LEVEL");
            alert.setVehicle(vehicle);
            create(alert);
        }

        if (reading.getFuelVolume() < 0.1 * vehicle.getMaxFuelVolume()) {
            Alert alert = new Alert();
            alert.setLevel("MEDIUM");
            alert.setType("FUEL LEVEL LOW");
            alert.setVehicle(vehicle);
            create(alert);
        }

        if (checkTirePressure(reading.getTires())) {
            Alert alert = new Alert();
            alert.setLevel("LOW");
            alert.setType("TIRE PRSSURE OFF");
            alert.setVehicle(vehicle);
            create(alert);
        }

        if (reading.getEngineCoolantLow() || reading.getCheckEngineLightOn()) {
            Alert alert = new Alert();
            alert.setLevel("LOW");
            alert.setType("TIRE PRESSURE OFF");
            alert.setVehicle(vehicle);
            create(alert);
        }
    }

    public boolean checkTirePressure(Tires tires) {
        return (tires.getFrontLeft() < 32 || tires.getFrontLeft() > 36) &&
                (tires.getFrontRight() < 32 || tires.getFrontRight() > 36) &&
                (tires.getRearLeft() < 32 || tires.getRearLeft() > 36) &&
                (tires.getRearRight() < 32 || tires.getRearRight() > 36);
    }
}
