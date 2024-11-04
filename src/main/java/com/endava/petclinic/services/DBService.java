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

public class DBServiceOwner {
    private static final Logger LOGGER = LogManager.getLogger(DBServiceOwner.class);
    public Owner getOwnerById(Long id) {
        LOGGER.info("Starting SELECT query for owner with ID: {}", id);

        String dbUrl = EnvReader.getDBUrl();
        String dbUsername = EnvReader.getDbUsername();
        String dbPassword = EnvReader.getDbPassword();

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {

            // Map the database columns to Java properties
            var mapColumnsToProperties = new HashMap<String, String>();
            mapColumnsToProperties.put("first_name", "firstName");
            mapColumnsToProperties.put("last_name", "lastName");

            BeanProcessor beanProcessor = new BeanProcessor(mapColumnsToProperties);
            RowProcessor rowProcessor = new BasicRowProcessor(beanProcessor);
            ResultSetHandler<Owner> handler = new BeanHandler<>(Owner.class, rowProcessor);

            // Execute the query
            QueryRunner runner = new QueryRunner();
            Owner owner = runner.query(conn, "SELECT * FROM owners WHERE id = ?", handler, id);

            LOGGER.info("SELECT query completed for owner with ID: {}. Result: {}", id, owner);
            return owner;

        } catch (SQLException e) {
            LOGGER.error("Failed to connect to the database for owner with ID: {}", id, e);
            throw new RuntimeException("Can't connect to the database", e);
        }
    }
}