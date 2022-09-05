package com.arsen.epam.internet.shop.repository.product.specification;

import com.arsen.epam.internet.shop.repository.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * Product id specification
 * Find specific product by id
 *
 * @author Arsen Sydoryk
 */
public class ProductIdSpecification implements Specification {

    private final int id;

    public ProductIdSpecification(int id) {
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
        return "SELECT * FROM products_view WHERE id = ?";
    }
}
