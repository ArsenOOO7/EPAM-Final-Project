package com.arsen.epam.internet.shop.repository.image.specification;

import com.arsen.epam.internet.shop.repository.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Image id specification
 * Specification to find image by id
 *
 * @author Arsen Sydoryk
 */
public class ImageIdSpecification implements Specification {

    private final int id;

    public ImageIdSpecification(int id) {
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
        return "SELECT * FROM images WHERE id = ?";
    }
}
