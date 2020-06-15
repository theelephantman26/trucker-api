package com.bhalchandra.entity;

import org.springframework.stereotype.Component;

@Component
public class GeoLocation {
    Float latitude;
    Float longitude;

    public GeoLocation() {

    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }
}
