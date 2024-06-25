package com.example.entity;

import java.util.List;

public class Category {
    private Integer id;
    private String name;
    private List<Impact_Area> impact_areas;
    // Getters and Setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Impact_Area> getImpact_areas() {
        return impact_areas;
    }

    public void setImpact_areas(List<Impact_Area> impact_areas) {
        this.impact_areas = impact_areas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
