package com.mileshko.lesya.dao;

import com.mileshko.lesya.entity.Manufacturer;
import com.mileshko.lesya.entity.Souvenir;
import com.mileshko.lesya.exception.ManufacturerDaoException;
import com.mileshko.lesya.exception.SouvenirDaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SouvenirDbDao implements SouvenirDao {
    private static final String SELECT
            = "SELECT id, name, manufacturer_id, release_date, price FROM souvenir ORDER BY name";
    private static final String SELECT_ONE
            = "SELECT id, name, manufacturer_id, release_date, price FROM souvenir WHERE id=?";
    private static final String INSERT
            = "INSERT INTO souvenir (name, manufacturer_id, release_date, price) VALUES ( ?, ?)";
    private static final String UPDATE
            = "UPDATE souvenir SET name=?, manufacturer_id=?, release_date=?, price=? WHERE id=?";
    private static final String DELETE
            = "DELETE FROM souvenir WHERE id=?";

    private ConnectionBuilder builder = new SimpleConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    private Souvenir fillSouvenir(ResultSet rs) throws SQLException {
        Souvenir souvenir = new Souvenir();
        souvenir.setId(rs.getLong("id"));
        souvenir.setName(rs.getString("name"));
        souvenir.setManufacturer(rs.getLong("manufacturer_id"));
        return souvenir;
    }

    @Override
    public Long addSouvenir(Souvenir souvenir) throws SouvenirDaoException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT, new String[]{"id"})) {
            Long id = -1L;
            pst.setString(1, souvenir.getName());
            pst.setLong(2, souvenir.getManufacturer());
            pst.setDate(3, (Date) souvenir.getReleaseDate());
            pst.setInt(4, souvenir.getPrice());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                id = gk.getLong("id");
            }
            gk.close();
            return id;
        } catch (Exception e) {
            throw new SouvenirDaoException(e);
        }
    }

    @Override
    public void updateSouvenir(Souvenir souvenir) throws SouvenirDaoException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setString(1, souvenir.getName());
            pst.setLong(2, souvenir.getManufacturer());
            pst.setDate(3, (Date) souvenir.getReleaseDate());
            pst.setInt(4, souvenir.getPrice());
            pst.setLong(5, souvenir.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new SouvenirDaoException(e);
        }
    }

    @Override
    public void deleteSouvenir(Long id) throws SouvenirDaoException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE)) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new SouvenirDaoException(e);
        }
    }

    @Override
    public Souvenir getSouvenir(Long id) throws SouvenirDaoException {
        Souvenir souvenir = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst = con.prepareStatement(SELECT_ONE);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                souvenir = fillSouvenir(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            throw new SouvenirDaoException(e);
        }
        return souvenir;
    }

    @Override
    public List<Souvenir> findSouvenirs() throws SouvenirDaoException {
        List<Souvenir> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillSouvenir(rs));
            }
            rs.close();
        } catch (Exception e) {
            throw new SouvenirDaoException(e);
        }
        return list;
    }
}
