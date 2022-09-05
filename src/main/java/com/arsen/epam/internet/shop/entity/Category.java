package com.arsen.epam.internet.shop.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Category entity
 *
 * @author Arsen Sydoryk
 */
public class Category implements Entity{

    private int id;

    private String identifier;
    private String localeUA;
    private String localeEN;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLocaleUA() {
        return localeUA;
    }

    public void setLocaleUA(String localeUA) {
        this.localeUA = localeUA;
    }

    public String getLocaleEN() {
        return localeEN;
    }

    public void setLocaleEN(String localeEN) {
        this.localeEN = localeEN;
    }

    @Override
    public void extract(ResultSet resultSet) throws SQLException {

        id = resultSet.getInt("id");

        identifier = resultSet.getString("identifier");
        localeUA = resultSet.getString("locale_ua");
        localeEN = resultSet.getString("locale_en");

    }
}
