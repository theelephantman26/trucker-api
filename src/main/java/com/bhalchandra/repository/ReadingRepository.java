package com.bhalchandra.repository;

import com.bhalchandra.entity.Alert;
import com.bhalchandra.entity.Reading;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReadingRepository extends CrudRepository<Reading, String> {
    @Query("SELECT reading FROM Reading reading WHERE date(reading.timestamp) >= :pastDate and reading.vin = :vehicleId")
    public List<Reading> findGeolocationSinceMinutes(@Param("pastDate") Date pastDate, @Param("vehicleId") String vehicleId);
}
