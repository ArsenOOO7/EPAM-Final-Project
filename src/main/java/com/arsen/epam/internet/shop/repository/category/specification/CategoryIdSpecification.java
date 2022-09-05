package com.arsen.epam.internet.shop.repository.category.specification;

import com.arsen.epam.internet.shop.repository.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Category id specification
 * Helps to find category by id
 *
 * @author Arsen Sydoryk
 */
public class CategoryIdSpecification implements Specification {

    private final int id;

    public CategoryIdSpecification(int id) {
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
        return "SELECT * FROM categories WHERE id = ?";
    }
}
