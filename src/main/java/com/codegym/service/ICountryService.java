package com.codegym.service;

import com.codegym.model.Country;

import java.util.Optional;

public interface ICountryService {
    Iterable<Country> findAll();

    Optional<Country> findById(Long id);

    Country edit(Country country);

    void deleteById(Long id);
}
