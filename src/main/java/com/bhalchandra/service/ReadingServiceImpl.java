package com.bhalchandra.service;

import com.bhalchandra.entity.Alert;
import com.bhalchandra.entity.Reading;
import com.bhalchandra.entity.Tires;
import com.bhalchandra.entity.Vehicle;
import com.bhalchandra.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReadingServiceImpl implements ReadingService {
    @Autowired
    ReadingRepository repository;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    AlertService alertService;

    @Override
    public List<Reading> findAll() {
        return (List<Reading>) repository.findAll();
    }

    @Override
    public Reading create(Reading reading) {
        reading = repository.save(reading);
        checkAndCreateAlerts(reading);
        return reading;
    }

    @Async
    public void checkAndCreateAlerts(Reading reading) {
        Vehicle vehicle = vehicleService.findOne(reading.getVin());
        if (reading.getEngineRpm() > vehicle.getRedlineRpm()) {
            Alert alert = new Alert();
            alert.setLevel("HIGH");
            alert.setType("ENGINE RPM LEVEL");
            alertService.create(alert);
        }

        if (reading.getFuelVolume() < 0.1 * vehicle.getMaxFuelVolume()) {
            Alert alert = new Alert();
            alert.setLevel("MEDIUM");
            alert.setType("FUEL LEVEL LOW");
            alertService.create(alert)
        }

        if (checkTirePressure(reading.getTires())) {
            Alert alert = new Alert();
            alert.setLevel("LOW");
            alert.setType("TIRE PRSSURE OFF");
            alertService.create(alert);
        }

        if (reading.getEngineCoolantLow() || reading.getCheckEngineLightOn()) {
            Alert alert = new Alert();
            alert.setLevel("LOW");
            alert.setType("TIRE PRESSURE OFF");
            alertService.create(alert);
        }
    }

    public boolean checkTirePressure(Tires tires) {
        return (tires.getFrontLeft() < 32 || tires.getFrontLeft() > 36) &&
                (tires.getFrontRight() < 32 || tires.getFrontRight() > 36) &&
                (tires.getRearLeft() < 32 || tires.getRearLeft() > 36) &&
                (tires.getRearRight() < 32 || tires.getRearRight() > 36);
    }
}
