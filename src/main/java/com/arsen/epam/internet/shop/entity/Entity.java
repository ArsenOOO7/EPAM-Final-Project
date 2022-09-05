package com.arsen.epam.internet.shop.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Root of all entities
 *
 * @author Arsen Sydoryk
 *
 */
public interface Entity {

    void setId(int id);
    int getId();

    /**
     * Method initializes entity
     *
     * @param resultSet as a result of querying to DB
     * @throws SQLException
     *  if something go wrong while extracting data
     */
    void extract(ResultSet resultSet) throws SQLException;

}
