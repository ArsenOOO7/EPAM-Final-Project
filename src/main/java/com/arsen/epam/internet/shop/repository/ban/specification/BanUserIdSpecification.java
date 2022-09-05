package com.arsen.epam.internet.shop.repository.ban.specification;

import com.arsen.epam.internet.shop.repository.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Ban user id specification
 * Find ban info by user id
 *
 * @author Arsen Sydoruk
 */
public class BanUserIdSpecification implements Specification {

    private final int userId;

    public BanUserIdSpecification(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public PreparedStatement getStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(getQuery());
        preparedStatement.setInt(1, userId);

        return preparedStatement;
    }

    @Override
    public String getQuery() {
        return "SELECT * FROM users_block WHERE user_id = ?";
    }
}
