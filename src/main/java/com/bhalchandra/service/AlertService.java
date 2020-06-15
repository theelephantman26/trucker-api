package com.bhalchandra.service;

import com.bhalchandra.entity.Alert;
import com.bhalchandra.entity.PastGeolocationResponse;
import com.bhalchandra.entity.Reading;

import java.util.List;

public interface AlertService {
    public List<Alert> findAll();

    public Alert create(Alert alert);

    public void checkAndCreateAlerts(Reading reading);

    public List<Alert> findAllWithinThePastHours(Integer pastHours);

    public List<Alert> findAllForVehicle(String vehicleId);
}
