package com.arsen.epam.internet.shop.repository.product.specification;

import com.arsen.epam.internet.shop.entity.product.sort.Sort;
import com.arsen.epam.internet.shop.repository.Specification;
import com.arsen.epam.internet.shop.service.data.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * Product all specification
 * This specification helps to find products by different search parameters from form
 *
 * @author Arsen Sydoryk
 */
public class ProductAllSpecification implements Specification {

    private int page = 1;

    private String search = "";

    private int minPrice = 0;
    private int maxPrice = 0;

    private int category = 0;
    private int color = 0;

    private int minSize = 0;
    private int maxSize = 0;

    private Sort sort = Sort.NOVELTY;

    private String select = "*";

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getMinSize() {
        return minSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
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
        int column = 1;

        if(!search.isEmpty()){
            preparedStatement.setString(column++, search + "%");
        }

        if(minPrice > 0){
            preparedStatement.setInt(column++, minPrice);
        }

        if(maxPrice > 0){
            preparedStatement.setInt(column++, maxPrice);
        }

        if(category > 0){
            preparedStatement.setInt(column++, category);
        }

        if(color > 0){
            preparedStatement.setInt(column++, color);
        }

        if(minSize > 0){
            preparedStatement.setInt(column++, minSize);
        }

        if(maxSize > 0){
            preparedStatement.setInt(column++, maxSize);
        }

        preparedStatement.setInt(column, (page - 1) * Data.MAX_ENTITIES_PAGE);

        return preparedStatement;
    }

    @Override
    public String getQuery() {
        String sql = "SELECT " + select + " FROM products_view ";
        String where = "WHERE";

        if(!search.isEmpty()){
            sql += where + " shortname LIKE ?";
            where = " AND ";
        }

        if(minPrice > 0){
            sql += where + " price >= ?";
            where = " AND ";
        }

        if(maxPrice > 0){
            sql += where + " price <= ?";
            where = " AND ";
        }

        if(category > 0){
            sql += where + " category_id = ?";
            where = " AND ";
        }

        if(color > 0){
            sql += where + " color = ?";
            where = " AND ";
        }

        if(minSize > 0){
            sql += where + " size_value >= ?";
            where = " AND ";
        }

        if(maxSize > 0){
            sql += where + " size_value <= ?";
            where = " AND ";
        }

        if(sort != Sort.UNDEFINED){
            sql += " ORDER BY " + sort.getField() + " " + sort.getOrder();
        }

        sql += " LIMIT " + Data.MAX_ENTITIES_PAGE + " OFFSET ?";

        return sql;

    }
}
