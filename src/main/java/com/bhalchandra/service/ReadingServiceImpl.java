package com.bhalchandra.service;

import com.bhalchandra.entity.*;
import com.bhalchandra.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ReadingServiceImpl implements ReadingService {
    @Autowired
    ReadingRepository repository;

    @Autowired
    AlertService alertService;

    @Override
    public List<Reading> findAll() {
        return (List<Reading>) repository.findAll();
    }

    @Override
    public Reading create(Reading reading) {
        reading = repository.save(reading);
        alertService.checkAndCreateAlerts(reading);
        return reading;
    }

    @Override
    public List<PastGeolocationResponse> findGeolocationWithinPastMinutes(String vehicleId, Integer pastMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -pastMinutes);
        Date date = calendar.getTime();

        ArrayList<PastGeolocationResponse> results = new ArrayList<PastGeolocationResponse>();
        List<Reading> records = repository.findGeolocationSinceMinutes(date, vehicleId);
        records.forEach(record -> {
            PastGeolocationResponse result = new PastGeolocationResponse();
            result.setVehicleId(record.getVin());

            GeoLocation geoLocation = new GeoLocation();
            geoLocation.setLatitude(record.getLatitude());
            geoLocation.setLongitude(record.getLongitude());

            result.setGeoLocation(geoLocation);

            results.add(result);
        });
        return (List<PastGeolocationResponse>) results;
    }
}
