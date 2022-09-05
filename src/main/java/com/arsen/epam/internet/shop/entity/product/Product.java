package com.arsen.epam.internet.shop.entity.product;

import com.arsen.epam.internet.shop.entity.Entity;
import com.arsen.epam.internet.shop.entity.product.color.Color;
import com.arsen.epam.internet.shop.entity.product.size.SizeUnit;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Product entity
 *
 * @author Arsen Sydoryk
 */
public class Product implements Entity {

    private int id;

    private String shortName;
    private String fullName;
    private String description;

    private int amount;
    private double price;

    private Color color;
    private SizeUnit sizeUnit;
    private int sizeValue;

    private int categoryId;
    private int boughtCounter;

    private int previewImageId;

    private int startDate;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(int colorId){
        setColor(Color.getInstance(colorId));
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public SizeUnit getSizeUnit() {
        return sizeUnit;
    }

    public void setSizeUnit(String sizeUnit){
        setSizeUnit(SizeUnit.getValue(sizeUnit));
    }

    public void setSizeUnit(int sizeUnitId){
        setSizeUnit(SizeUnit.getValue(sizeUnitId));
    }

    public void setSizeUnit(SizeUnit sizeUnit) {
        this.sizeUnit = sizeUnit;
    }

    public int getSizeValue() {
        return sizeValue;
    }

    public void setSizeValue(int sizeValue) {
        this.sizeValue = sizeValue;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBoughtCounter() {
        return boughtCounter;
    }

    public void setBoughtCounter(int boughtCounter) {
        this.boughtCounter = boughtCounter;
    }

    public int getPreviewImageId() {
        return previewImageId;
    }

    public void setPreviewImageId(int previewImageId) {
        this.previewImageId = previewImageId;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    @Override
    public void extract(ResultSet resultSet) throws SQLException {

        id = resultSet.getInt("id");

        shortName = resultSet.getString("shortname");
        fullName = resultSet.getNString("fullname");
        description = resultSet.getString("description");

        amount = resultSet.getInt("amount");
        price = resultSet.getDouble("price");

        setColor(resultSet.getInt("color"));
        setSizeUnit(resultSet.getInt("size_unit"));
        setSizeValue(resultSet.getInt("size_value"));

        categoryId = resultSet.getInt("category_id");
        boughtCounter = resultSet.getInt("bought_counter");

        previewImageId = resultSet.getInt("preview_image");

        startDate = resultSet.getInt("start_date");

    }
}
