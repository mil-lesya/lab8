package com.mileshko.lesya.dao;

import com.mileshko.lesya.entity.Manufacturer;
import com.mileshko.lesya.exception.ManufacturerDaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ManufacturerDbDao implements ManufacturerDao {
    private static final String SELECT
            = "SELECT id, name, country FROM manufacturer ORDER BY name";
    private static final String SELECT_ONE
            = "SELECT id, name, country FROM manufacturer WHERE id=?";
    private static final String INSERT
            = "INSERT INTO manufacturer (name, country) VALUES ( ?, ?)";
    private static final String UPDATE
            = "UPDATE manufacturer SET name=?, country=? WHERE id=?";
    private static final String DELETE
            = "DELETE FROM manufacturer WHERE id=?";

    private ConnectionBuilder builder = new SimpleConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    private Manufacturer fillManufacturer(ResultSet rs) throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(rs.getLong("id"));
        manufacturer.setName(rs.getString("name"));
        manufacturer.setCountry(rs.getString("country"));
        return manufacturer;
    }

    @Override
    public Long addManufacturer(Manufacturer manufacturer) throws ManufacturerDaoException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT, new String[]{"id"})) {
            Long id = -1L;
            pst.setString(1, manufacturer.getName());
            pst.setString(2, manufacturer.getCountry());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                id = gk.getLong("id");
            }
            gk.close();
            return id;
        } catch (Exception e) {
            throw new ManufacturerDaoException(e);
        }
    }

    @Override
    public void updateManufacturer(Manufacturer manufacturer) throws ManufacturerDaoException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setString(1, manufacturer.getName());
            pst.setString(2, manufacturer.getCountry());
            pst.setLong(3, manufacturer.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new ManufacturerDaoException(e);
        }
    }

    @Override
    public void deleteManufacturer(Long id) throws ManufacturerDaoException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE)) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new ManufacturerDaoException(e);
        }
    }

    @Override
    public Manufacturer getManufacturer(Long id) throws ManufacturerDaoException {
        Manufacturer manufacturer = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst = con.prepareStatement(SELECT_ONE);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                manufacturer = fillManufacturer(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            throw new ManufacturerDaoException(e);
        }
        return manufacturer;
    }

    @Override
    public List<Manufacturer> findManufacturers() throws ManufacturerDaoException {
        List<Manufacturer> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillManufacturer(rs));
            }
        } catch (Exception e) {
            throw new ManufacturerDaoException(e);
        }
        return list;
    }
}
