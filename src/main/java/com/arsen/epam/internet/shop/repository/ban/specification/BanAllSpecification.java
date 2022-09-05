package com.arsen.epam.internet.shop.repository.ban.specification;

import com.arsen.epam.internet.shop.repository.Specification;
import com.arsen.epam.internet.shop.service.data.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Ban all specification
 * Not, specification does not ban everyone :)
 * It helps to find all banned users
 *
 * @author Arsen Sydoryk
 */
public class BanAllSpecification implements Specification {

    private final int page;

    public BanAllSpecification(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    @Override
    public PreparedStatement getStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(getQuery());
        preparedStatement.setInt(1, (page - 1) * Data.MAX_ENTITIES_PAGE);

        return preparedStatement;
    }

    @Override
    public String getQuery() {
        return "SELECT * FROM users_block LIMIT " + Data.MAX_ENTITIES_PAGE + " OFFSET = ?";
    }
}
