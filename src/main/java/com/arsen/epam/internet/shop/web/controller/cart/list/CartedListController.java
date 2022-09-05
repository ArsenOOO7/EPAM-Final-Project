package com.arsen.epam.internet.shop.web.controller.cart.list;

import com.arsen.epam.internet.shop.repository.cart.specification.CartUserRegisteredSpecification;
import com.arsen.epam.internet.shop.repository.cart.specification.CartUserSpecification;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/cart/carted")
public class CartedListController extends CartListController{


    @Override
    protected CartUserSpecification getSpecification(int userId) {
        return new CartUserRegisteredSpecification(userId);
    }

    @Override
    protected String getType() {
        return "carted";
    }
}
