package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.model.Country;
import com.codegym.service.ICityService;
import com.codegym.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class CityController {
    @Autowired
    private ICityService cityService;

    @Autowired
    private ICountryService countryService;

    @GetMapping("/cities/create")
    public ModelAndView showAddForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        Iterable<Country> countries = countryService.findAll();
        modelAndView.addObject("countries", countries);
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/cities/create")
    public ModelAndView addNewCity(@Validated @ModelAttribute City city, BindingResult bindingResult) {
        city.validate(city, bindingResult);
        ModelAndView modelAndView;
        if (bindingResult.hasFieldErrors()) {
            modelAndView = new ModelAndView("/create");
            Iterable<Country> countries = countryService.findAll();
            modelAndView.addObject("countries", countries);
            modelAndView.addObject("message", "Success!!");
            return modelAndView;
        }
        cityService.edit(city);
        Iterable<Country> countries = countryService.findAll();
        modelAndView = new ModelAndView("/create");
        modelAndView.addObject("city", new City());
        modelAndView.addObject("countries", countries);
        return modelAndView;
    }

    @GetMapping("/cities")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("cities", cityService.findAll());
        modelAndView.addObject("country", countryService.findAll());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCity(@PathVariable Long id){
        Optional<City> cityOptional = cityService.findById(id);
        if(!cityOptional.isPresent()){
            return new ModelAndView("/404");
        }
        ModelAndView modelAndView = new ModelAndView("/edit");
        Iterable<Country> countries = countryService.findAll();
        modelAndView.addObject("countries", countries);
        modelAndView.addObject("city", cityOptional.get());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveEdit(@Validated @ModelAttribute City city, BindingResult bindingResult){
        city.validate(city, bindingResult);
        ModelAndView modelAndView = new ModelAndView("/edit");
        if (bindingResult.hasFieldErrors()) {
            modelAndView = new ModelAndView("/edit");
            Iterable<Country> countries = countryService.findAll();
            modelAndView.addObject("countries", countries);
            return modelAndView;
        }
        modelAndView.addObject("message", "Success!!");
        cityService.edit(city);
        return modelAndView;
    }


    @GetMapping("/delete/{id}")
    public ModelAndView deleteCity(@PathVariable Long id){
        Optional<City> cityOptional = cityService.findById(id);
        if(!cityOptional.isPresent()){
            return new ModelAndView("/404");
        }
        cityService.deleteById(id);
        return new ModelAndView("redirect:/cities");
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewDetail(@PathVariable Long id){
        Optional<City> cityOptional = cityService.findById(id);
        if(!cityOptional.isPresent()){
            return new ModelAndView("/404");
        }
        return new ModelAndView("/view", "city", cityOptional.get());
    }
}
