package com.tecnocomfenalco.edurag.eduoptimaai.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String specialty;

    @Min(1)
    private int maxCapacity;

    public Tutor() {
    }

    public Tutor(Long id, String name, String specialty, int maxCapacity) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.maxCapacity = maxCapacity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}