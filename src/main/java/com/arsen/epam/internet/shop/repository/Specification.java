package com.arsen.epam.internet.shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Specification of search in DB
 *
 * @author Arsen Sydoryk
 */
public interface Specification {


    /**
     * Returns PreparedStatement for future sql query
     *
     * @param connection conn
     * @return PreparedStatement
     * @throws SQLException
     *  if something go wrong while preparing statement
     */
    PreparedStatement getStatement(Connection connection) throws SQLException;

    /**
     * Dynamically builds SQL query
     *
     * @return query
     */
    String getQuery();

}
