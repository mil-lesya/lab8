package com.mileshko.lesya.entity;

import java.util.Date;

public class Souvenir {
    private Long id;
    private String name;
    private Long manufacturerId;
    private Date releaseDate;
    private Integer price;

    public Souvenir() {
    }

    public Souvenir(String name, Long manufacturerId, Date releaseDate, Integer price) {
        this.name = name;
        this.manufacturerId = manufacturerId;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public Souvenir(Long id, String name, Long manufacturerId, Date releaseDate, Integer price) {
        this.id = id;
        this.name = name;
        this.manufacturerId = manufacturerId;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getManufacturer() {
        return manufacturerId;
    }

    public void setManufacturer(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Souvenir{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturer=" + manufacturerId +
                ", releaseDate=" + releaseDate +
                ", price=" + price +
                '}';
    }
}
