package com.bhalchandra.service;

import com.bhalchandra.entity.Reading;

import java.util.List;

public interface ReadingService {
    List<Reading> findAll();

    Reading findOne();

    Reading create();

    Reading update(Reading vehicle);
}
