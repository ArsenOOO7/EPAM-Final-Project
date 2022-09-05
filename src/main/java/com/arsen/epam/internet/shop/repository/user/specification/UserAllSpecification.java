package com.arsen.epam.internet.shop.repository.user.specification;

import com.arsen.epam.internet.shop.repository.Specification;
import com.arsen.epam.internet.shop.service.data.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * User all specification
 * Class accepts as parameters:
 *  page - for pagination;
 *  select - selecting count of rows | full rows
 *  name - select users whose name is like {value}
 *  banned - only banned users
 *
 * @author Arsen Sydoryk
 */
public class UserAllSpecification implements Specification {

    private final int page;

    private String select = "*";
    private String name = "";

    private boolean banned = false;

    public UserAllSpecification(int page) {
        this.page = page;
    }

    public void setCountAll(){
        select = "COUNT(*)";
    }

    public void setName(String name) {
        if(name != null) {
            this.name = name + "%";
        }
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    @Override
    public PreparedStatement getStatement(Connection connection) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getQuery());

        int column = 1;
        if(!name.isEmpty()){
            preparedStatement.setString(column++, name);
        }

        preparedStatement.setInt(column, (page - 1) * Data.MAX_ENTITIES_PAGE);
        return preparedStatement;
    }


    @Override
    public String getQuery() {
        String sql = "SELECT "+ select +" FROM users_view WHERE status = 0";
        if(!name.isEmpty()){
            sql += "  AND name LIKE ?";
        }

        if(banned){
            sql += " AND blocked = 1";
        }

        return sql + " LIMIT " + Data.MAX_ENTITIES_PAGE +" OFFSET ?"
;    }

}
