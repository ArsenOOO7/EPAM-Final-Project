package com.arsen.epam.internet.shop.repository.ban.specification;

import com.arsen.epam.internet.shop.repository.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Ban id specification
 * Find ban info by row id
 *
 * @author Arsen Sydoruk
 */
public class BanIdSpecification implements Specification {

    private final int id;

    public BanIdSpecification(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    @Override
    public PreparedStatement getStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(getQuery());
        preparedStatement.setInt(1, id);

        return preparedStatement;
    }

    @Override
    public String getQuery() {
        return "SELECT * FROM users_block WHERE id = ?";
    }
}