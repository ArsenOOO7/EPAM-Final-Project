package com.arsen.epam.internet.shop.repository.category.specification;

import com.arsen.epam.internet.shop.repository.Specification;
import com.arsen.epam.internet.shop.service.data.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Category all specification
 * Specification helps to get all categories
 *
 * @author Arsen Sydoryk
 */
public class CategoryAllSpecification implements Specification {

    private final int page;

    public CategoryAllSpecification(int page) {
        this.page = page;
    }

    public CategoryAllSpecification(){
        this(0);
    }

    public int getPage() {
        return page;
    }

    @Override
    public PreparedStatement getStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(getQuery());

        if(page > 0) {
            preparedStatement.setInt(1, (page - 1) * Data.MAX_ENTITIES_PAGE);
        }
        return preparedStatement;
    }

    @Override
    public String getQuery() {
        String sql = "SELECT * FROM categories";
        if(page > 0){
            sql += " LIMIT " + Data.MAX_ENTITIES_PAGE + " OFFSET ?";
        }

        return sql;
    }
}
