package com.mileshko.lesya.dao;

import com.mileshko.lesya.entity.Manufacturer;
import com.mileshko.lesya.exception.ManufacturerDaoException;

import java.util.List;

public interface ManufacturerDao {
    public Long addManufacturer(Manufacturer manufacturer) throws ManufacturerDaoException;
    public void updateManufacturer(Manufacturer Manufacturer) throws ManufacturerDaoException;
    public void deleteManufacturer(Long id) throws ManufacturerDaoException;
    public Manufacturer getManufacturer(Long id) throws ManufacturerDaoException;
    public List<Manufacturer> findManufacturers() throws ManufacturerDaoException;
}
