package com.arsen.epam.internet.shop.repository.user.specification;

import com.arsen.epam.internet.shop.repository.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * User id specification
 * Class accepts user id as parameter and give possibility to find specific user
 *
 * @author Arsen Sydoryk
 */
public class UserIdSpecification implements Specification {

    private final int id;

    public UserIdSpecification(int id) {
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
        return "SELECT * FROM users_view WHERE id = ?";
    }
}
