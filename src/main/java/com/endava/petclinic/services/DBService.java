package com.endava.petclinic.services;

import com.endava.petclinic.model.Owner;
import com.endava.petclinic.util.EnvReader;
import lombok.var;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class DBService {
    private static final Logger LOGGER = LogManager.getLogger(DBService.class);
    public Owner getOwnerById(Long id) {
        LOGGER.info("Starting SELECT query for owner with ID: {}", id);
        try(Connection conn = DriverManager.getConnection(EnvReader.getDBUrl(),EnvReader.getDbUsername(),EnvReader.getDbPassword())) {


            var mapColumnsToProperties = new HashMap<String, String>();
            //mapping your db to entity here;
            mapColumnsToProperties.put("first_name","firstName");
            mapColumnsToProperties.put("last_name","lastName");
            BeanProcessor beanProcessor = new BeanProcessor(mapColumnsToProperties);
            RowProcessor rowProcessor = new BasicRowProcessor(beanProcessor);

            ResultSetHandler<Owner> h = new BeanHandler<Owner>(Owner.class, rowProcessor);
            QueryRunner runner = new QueryRunner();
            Owner owner = runner.query(conn, "SELECT * from owners where id = ?", h, id );
            LOGGER.info("SELECT query completed for owner with ID: {}. Result: {}", id, owner);
            return owner;
        } catch (SQLException e) {
            LOGGER.error("Failed to connect to the database for owner with ID: {}", id, e);
            throw new RuntimeException("Can t cnnect to DB ",e);
        }

    }

}
