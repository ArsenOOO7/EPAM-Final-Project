package com.arsen.epam.internet.shop.repository.user.specification;

import com.arsen.epam.internet.shop.repository.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * User login specification
 * In most cases, class is for login/register
 *
 * It accepts 3 parameters: login, email & password. Then dynamically builds query to find specific user
 *
 * @author Arsen Sydoryk
 */
public class UserLoginSpecification implements Specification {


    private String login = "";
    private String email = "";
    private String password = "";

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void clear(){
        login = "";
        email = "";
        password = "";
    }

    @Override
    public PreparedStatement getStatement(Connection connection) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getQuery());

        if(email.isEmpty()) {
            preparedStatement.setString(1, login);
        }else{
            preparedStatement.setString(1, email);
        }
        if(!password.isEmpty()){
            preparedStatement.setString(2, password);
        }

        return preparedStatement;

    }

    @Override
    public String getQuery() {
        String query = "SELECT * FROM users_view WHERE";
        if(login.isEmpty()){
            query += " email = ?";
        }else{
            query += " login = ?";
        }
        if(!password.isEmpty()){
            query += " AND password = ?";
        }

        return query;
    }

}
