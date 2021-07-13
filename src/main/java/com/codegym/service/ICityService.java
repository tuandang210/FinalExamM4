package com.codegym.service;

import com.codegym.model.City;

import java.util.Optional;

public interface ICityService {
    Iterable<City> findAll();

    Optional<City> findById(Long id);

    void edit(City city);

    void deleteById(Long id);

}
