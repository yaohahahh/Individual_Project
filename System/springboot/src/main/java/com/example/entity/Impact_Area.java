package com.example.entity;

public class Impact_Area {
    private Integer id;
    private String name;
    private Float point;
    private Float total_point;

    // Getters and Setters
    public Float getTotal_point() {
        return total_point;
    }

    public void setTotal_point(Float total_point) {
        this.total_point = total_point;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getPoint() {
        return point;
    }

    public void setPoint(Float point) {
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
