package com.arsen.epam.internet.shop.database;


/**
 * Class with SQL queries
 *
 *
 */
public class Query {

    //User query
    public final static String SAVE_USER = "INSERT INTO users VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public final static String UPDATE_USER =
                    "UPDATE users SET name = ?," +
                    "surname = ?," +
                    "email = ?," +
                    "login = ?," +
                    "password = ?," +
                    "birth_date = ?," +
                    "balance = ?," +
                    "status = ?," +
                    "avatar = ? " +
                    "WHERE id = ?";

    public final static String DELETE_USER = "DELETE FROM users WHERE id = ?";

    //Product query
    public final static String SAVE_PRODUCT = "INSERT INTO products VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, UNIX_TIMESTAMP())";
    public final static String UPDATE_PRODUCT =
            "UPDATE products SET shortname = ?," +
                    "fullname = ?," +
                    "description = ?," +
                    "amount = ?," +
                    "price = ?," +
                    "color = ?," +
                    "size_unit = ?," +
                    "size_value = ?," +
                    "category_id = ?," +
                    "preview_image = ?," +
                    "start_date = ? " +
                    "WHERE id = ?";

    public final static String DELETE_PRODUCT = "DELETE FROM products WHERE id = ?";


    //Category queries
    public final static String SAVE_CATEGORY = "INSERT INTO categories VALUES(DEFAULT, ?, ?, ?)";
    public final static String UPDATE_CATEGORY =
            "UPDATE categories SET identifier = ?, locale_ua = ?, locale_en = ? WHERE id = ?";
    public final static String DELETE_CATEGORY = "DELETE FROM categories WHERE id = ?";


    //User ban queries
    public final static String SAVE_BAN = "INSERT INTO users_block VALUES(DEFAULT, ?, ?, UNIX_TIMESTAMP(), UNIX_TIMESTAMP() + (3600 * ?))";
    public final static String UPDATE_BAN =
            "UPDATE users_block SET user_id = ?, reason = ?, start_time = UNIX_TIMESTAMP(), end_time = UNIX_TIMESTAMP() + (3600 * ?) WHERE id = ?";
    public final static String DELETE_BAN = "DELETE FROM users_block WHERE id = ?";

    //Cart queries
    public final static String SAVE_CART = "INSERT INTO cart VALUES(DEFAULT, ?, ?, ?, ?, CURRENT_TIMESTAMP(), ?)";
    public final static String UPDATE_CART =
            "UPDATE cart SET user_id = ?, product_id = ?, amount = ?, price = ?, date = ?, status = ? WHERE id = ?";
    public final static String DELETE_CART = "DELETE FROM cart WHERE id = ?";

    //Cart queries
    public final static String SAVE_IMAGE = "INSERT INTO images VALUES(DEFAULT, ?, ?, ?)";
    public final static String UPDATE_IMAGE =
            "UPDATE images SET uuid = ?, mime = ?, data = ? WHERE id = ?";
    public final static String DELETE_IMAGE = "DELETE FROM images WHERE id = ?";


}
