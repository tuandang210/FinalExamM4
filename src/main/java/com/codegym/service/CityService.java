package com.codegym.service;

import com.codegym.model.City;
import com.codegym.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService implements ICityService {
    @Autowired
    private ICityRepository cityRepository;

    @Override
    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public void edit(City city) {
        cityRepository.save(city);
    }

    @Override
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }

}
