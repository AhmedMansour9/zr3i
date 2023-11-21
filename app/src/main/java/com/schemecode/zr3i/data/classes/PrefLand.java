package com.schemecode.zr3i.data.classes;

import java.io.Serializable;

public class PrefLand implements Serializable {
    private int id ;
    private String owner ;
    private String crops ;
    private String country ;
    private String city ;
    private String city_area ;
    private int numberOfCycles ;
    private int contractSpace ;
    private Double actualSpace ;
    private String desc ;

    public PrefLand(int id, String owner, String crops, String country, String city, String city_area, int numberOfCycles, int contractSpace, Double actualSpace, String desc) {
        this.id = id;
        this.owner = owner;
        this.crops = crops;
        this.country = country;
        this.city = city;
        this.city_area = city_area;
        this.numberOfCycles = numberOfCycles;
        this.contractSpace = contractSpace;
        this.actualSpace = actualSpace;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCrops() {
        return crops;
    }

    public void setCrops(String crops) {
        this.crops = crops;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity_area() {
        return city_area;
    }

    public void setCity_area(String city_area) {
        this.city_area = city_area;
    }

    public int getNumberOfCycles() {
        return numberOfCycles;
    }

    public void setNumberOfCycles(int numberOfCycles) {
        this.numberOfCycles = numberOfCycles;
    }

    public int getContractSpace() {
        return contractSpace;
    }

    public void setContractSpace(int contractSpace) {
        this.contractSpace = contractSpace;
    }

    public Double getActualSpace() {
        return actualSpace;
    }

    public void setActualSpace(Double actualSpace) {
        this.actualSpace = actualSpace;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
