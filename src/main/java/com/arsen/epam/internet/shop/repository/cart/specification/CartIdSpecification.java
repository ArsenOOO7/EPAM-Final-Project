package com.arsen.epam.internet.shop.repository.cart.specification;

import com.arsen.epam.internet.shop.repository.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Cart id specification
 * Helps to find cart by id
 *
 * @author Arsen Sydoryk
 */
public class CartIdSpecification implements Specification {

    private final int id;

    public CartIdSpecification(int id) {
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
        return "SELECT * FROM cart_view WHERE id = ?";
    }
}
