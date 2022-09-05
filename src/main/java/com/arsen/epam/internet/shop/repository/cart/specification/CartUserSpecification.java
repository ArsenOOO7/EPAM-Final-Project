package com.arsen.epam.internet.shop.repository.cart.specification;

import com.arsen.epam.internet.shop.repository.Specification;
import com.arsen.epam.internet.shop.service.data.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Cart user specification
 * Finds carts by user id and status
 * Implementations: CartUserRegisteredSpecification, CartUserPurchasedSpecification, CartUserCancelledSpecification
 *
 * @author Arsen Sydoryk
 */
public abstract class CartUserSpecification implements Specification {

    private final int userId;

    private int page = 1;

    private String select = "*";

    public CartUserSpecification(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public int getStatus(){
        return 0;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    @Override
    public PreparedStatement getStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(getQuery());
        preparedStatement.setInt(1, userId);

        if(page > 0){
            preparedStatement.setInt(2, (page - 1) * Data.MAX_ENTITIES_PAGE);
        }

        return preparedStatement;
    }

    @Override
    public String getQuery() {
        String sql =  "SELECT " + select + " FROM cart_view WHERE user_id = ? AND status = " + getStatus();
        sql += " ORDER BY date DESC";
        if(page > 0){
            sql += " LIMIT " + Data.MAX_ENTITIES_PAGE + " OFFSET ?";
        }

        return sql;
    }
}
