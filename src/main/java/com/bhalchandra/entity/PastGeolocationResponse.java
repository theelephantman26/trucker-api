package com.bhalchandra.entity;

import org.springframework.stereotype.Component;

@Component
public class PastGeolocationResponse {
    String vehicleId;
    GeoLocation geoLocation;

    public PastGeolocationResponse() {}

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }
}
