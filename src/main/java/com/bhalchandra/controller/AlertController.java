package com.bhalchandra.controller;

import com.bhalchandra.entity.Alert;
import com.bhalchandra.entity.GeoLocation;
import com.bhalchandra.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {
    @Autowired
    AlertService service;

    @RequestMapping(value = "/{hours}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Alert> getHighAlertsSince(@PathVariable("hours") Integer pastHours) {
        return service.findAllWithinThePastHours(pastHours);
    }

    @RequestMapping(value = "/vehicle/{vin}" , method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Alert> getAllAlertsForVehicle(@PathVariable("vin") String vehicleId) {
        return service.findAllForVehicle(vehicleId);
    }
}
