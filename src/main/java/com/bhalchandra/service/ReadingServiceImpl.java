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
}
