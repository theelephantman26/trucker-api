package com.bhalchandra.service;

import com.bhalchandra.entity.Alert;

import java.util.List;

public interface AlertService {
    public List<Alert> findAll();

    public Alert create(Alert alert);
}
