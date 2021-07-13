package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    @ManyToOne
    private Country country;
    @Min(1)
    private Double area;
    @Min(1)
    private Double population;
    @Min(1)
    private Double gpa;
    @Size(min = 20)
    private String description;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        City city = (City) target;
        Double area = city.getArea();
        Double population = city.getPopulation();
        Double gdp = city.getGpa();
        if (area <= 0){
            errors.rejectValue("area", "area1", "");
        }
        if (population <= 0){
            errors.rejectValue("population", "population1", "");
        }
        if (gdp <= 0){
            errors.rejectValue("gpa", "gpa1", "");
        }
    }
}
