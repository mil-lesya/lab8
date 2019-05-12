package com.mileshko.lesya;

import com.mileshko.lesya.config.GlobalConfig;
import com.mileshko.lesya.dao.ConnectionBuilder;
import com.mileshko.lesya.dao.ManufacturerDbDao;
import com.mileshko.lesya.dao.SimpleConnectionBuilder;
import com.mileshko.lesya.entity.Manufacturer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        try {
            GlobalConfig.initGlobalConfig();
            ConnectionBuilder builder = new SimpleConnectionBuilder();
            Connection con = builder.getConnection();
            System.out.println("Connected!");

            String query1 = "select * from souvenir  where manufacturer_id = 1";
            System.out.println("Все сувениры с заданным manufacturer_id");
            PreparedStatement pst = con.prepareStatement(query1);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }


            String query2 = "select * from souvenir  where release_date = '2019-04-09'";
            System.out.println("Все производители сувениров произведённых в определённую дату");
            pst = con.prepareStatement(query2);
            rs = pst.executeQuery();
            while (rs.next()) {
                rs.getString("manufacturer_id");
                Manufacturer manufacturer;
                ManufacturerDbDao manufacturerDbDao = new ManufacturerDbDao();
                manufacturer = manufacturerDbDao.getManufacturer(rs.getLong("manufacturer_id"));
                System.out.println(manufacturer.getName() + " " + manufacturer.getCountry());
            }

            String query3 = "select * from manufacturer  where country = 'USA'";
            System.out.println("Все сувениры произведённые в заданной стране ");
            pst = con.prepareStatement(query3);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("country"));
                String query = "select * from souvenir  where manufacturer_id =" + rs.getLong("id");
                pst = con.prepareStatement(query);
                ResultSet res = pst.executeQuery();
                while (res.next()) {
                    System.out.println(res.getString("name"));
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return;
        }
    }
}
