package com.arsen.epam.internet.shop.entity.cart;

import com.arsen.epam.internet.shop.entity.Entity;
import com.arsen.epam.internet.shop.entity.cart.status.PurchaseStatus;
import com.arsen.epam.internet.shop.entity.product.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


/**
 * Cart entity
 *
 * @author Arsen Sydoryk
 */
public class Cart implements Entity {

    private int id;

    private int userId;
    private int productId;
    private int amount;
    private int price;

    private Product product;
    private Timestamp date;
    private PurchaseStatus status;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public PurchaseStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseStatus status) {
        this.status = status;
    }

    public void setStatus(int status) {
        setStatus(PurchaseStatus.getValue(status));
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public void extract(ResultSet resultSet) throws SQLException {

        id = resultSet.getInt("id");

        userId = resultSet.getInt("user_id");
        productId = resultSet.getInt("product_id");

        amount = resultSet.getInt("amount");
        price = resultSet.getInt("price");

        date = resultSet.getTimestamp("date");
        setStatus(resultSet.getInt("status"));

    }
}
