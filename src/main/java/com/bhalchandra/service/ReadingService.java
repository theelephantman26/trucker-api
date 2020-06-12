package com.bhalchandra.service;

import com.bhalchandra.entity.Reading;

import java.util.List;

public interface ReadingService {
    List<Reading> findAll();

    Reading create(Reading reading);
}
