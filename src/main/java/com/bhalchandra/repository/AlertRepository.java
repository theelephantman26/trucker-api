package com.bhalchandra.repository;

import com.bhalchandra.entity.Alert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AlertRepository extends CrudRepository<Alert, String> {

    @Query("SELECT alert FROM Alert alert WHERE alert.createdAt > :pastDate and alert.level = 'HIGH' ORDER BY alert.createdAt")
    public List<Alert> findHighAlertsSince(@Param("pastDate") Date pastDate);

}
