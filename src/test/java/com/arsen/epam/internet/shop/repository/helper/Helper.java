package com.arsen.epam.internet.shop.repository.helper;

import com.arsen.epam.internet.shop.entity.Category;
import com.arsen.epam.internet.shop.entity.cart.Cart;
import com.arsen.epam.internet.shop.entity.cart.status.PurchaseStatus;
import com.arsen.epam.internet.shop.entity.product.Product;
import com.arsen.epam.internet.shop.entity.product.color.Color;
import com.arsen.epam.internet.shop.entity.product.size.SizeUnit;
import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.entity.user.ban.UserBan;
import com.arsen.epam.internet.shop.entity.user.status.UserStatus;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Helper {

    public static User createUser(String name, String surname){
        return createUser(name, surname, "", "", "", new Date(0), 0.0, 0, UserStatus.USER);
    }

    public static User createUser(String name, String surname, String email,
                                  String login, String password,
                                  Date birthDate,
                                  double balance, int avatar,
                                  UserStatus status){

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setBirthDate(birthDate);
        user.setLogin(login);
        user.setPassword(password);
        user.setBalance(balance);
        user.setAvatarId(avatar);
        user.setStatus(status);


        return user;
    }


    public static UserBan createBan(int userId, String reason, int hours){

        UserBan ban = new UserBan();
        ban.setUserId(userId);
        ban.setReason(reason);
        ban.setEndTime(hours);

        return ban;
    }


    public static Category createCategory(String identifier, String localeUA, String localEN){
        Category category = new Category();
        category.setIdentifier(identifier);
        category.setLocaleUA(localeUA);
        category.setLocaleEN(localEN);

        return category;
    }


    public static Product createProduct(String shortName, String fullName,
                                        String description, int amount, int price,
                                        Color color, SizeUnit sizeUnit,
                                        int sizeValue, int category, int counter, int previewImg, int startDate){

        Product product = new Product();
        product.setShortName(shortName);
        product.setFullName(fullName);
        product.setDescription(description);
        product.setAmount(amount);
        product.setPrice(price);
        product.setColor(color);
        product.setSizeUnit(sizeUnit);
        product.setSizeValue(sizeValue);
        product.setCategoryId(category);
        product.setBoughtCounter(counter);
        product.setPreviewImageId(previewImg);
        product.setStartDate(0);


        return product;

    }


    public static Cart createCart(int userId, int productId, int amount, int price, PurchaseStatus status){

        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setProductId(productId);
        cart.setAmount(amount);
        cart.setPrice(price);
        cart.setStatus(status);
        cart.setDate(Timestamp.valueOf(LocalDateTime.now()));

        return cart;
    }

}
