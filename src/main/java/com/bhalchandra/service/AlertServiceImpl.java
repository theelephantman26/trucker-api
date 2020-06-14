package com.bhalchandra.service;

import com.bhalchandra.entity.Alert;
import com.bhalchandra.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlertServiceImpl implements AlertService {
    @Autowired
    AlertRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Alert> findAll() {
        return (List<Alert>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public Alert create(Alert alert) {
        return repository.save(alert);
    }
}
