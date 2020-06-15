package com.bhalchandra.controller;

import com.bhalchandra.entity.GeoLocation;
import com.bhalchandra.entity.PastGeolocationResponse;
import com.bhalchandra.entity.Reading;
import com.bhalchandra.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/readings")
public class ReadingController {
    @Autowired
    ReadingService service;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reading> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public Reading create(@RequestBody Reading reading) {
        return service.create(reading);
    }

    @RequestMapping(value = "/geolocation/{vin}/{minutes}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PastGeolocationResponse> getGeoLocationWithinTimeframe(@PathVariable("minutes") Integer pastMinutes, @PathVariable("vin") String vin) {
        return service.findGeolocationWithinPastMinutes(vin, pastMinutes);
    }
}
