package com.mileshko.lesya;

import com.mileshko.lesya.config.GlobalConfig;
import com.mileshko.lesya.dao.ManufacturerDbDao;
import com.mileshko.lesya.entity.Manufacturer;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        try {
            GlobalConfig.initGlobalConfig();
            Manufacturer manufacturer = new Manufacturer("Axis","Belarus");
            ManufacturerDbDao manufacturerDbDao = new ManufacturerDbDao();
            manufacturerDbDao.addManufacturer(manufacturer);
            for (Manufacturer man :
                    manufacturerDbDao.findManufacturers()) {
                System.out.println(man);

            }
            System.out.println(manufacturerDbDao.getManufacturer(2L));
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return;
        }
    }
}
