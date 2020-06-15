package com.bhalchandra.service;

import com.bhalchandra.entity.PastGeolocationResponse;
import com.bhalchandra.entity.Reading;

import java.util.List;

public interface ReadingService {
    List<Reading> findAll();

    Reading create(Reading reading);

    public List<PastGeolocationResponse> findGeolocationWithinPastMinutes(String vehicleId, Integer pastMinutes);
}
