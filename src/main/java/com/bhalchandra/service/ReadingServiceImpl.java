package com.bhalchandra.service;

import com.bhalchandra.entity.Reading;
import com.bhalchandra.entity.Vehicle;
import com.bhalchandra.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingServiceImpl implements ReadingService {
    @Autowired
    ReadingRepository repository;

    @Override
    public List<Reading> findAll() {
        return (List<Reading>) repository.findAll();
    }

    @Override
    public Reading findOne() {
        return null;
    }

    @Override
    public Reading create() {
        return null;
    }

    @Override
    public Reading update(Reading vehicle) {
        return null;
    }
}
