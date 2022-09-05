package com.arsen.epam.internet.shop.web.controller.cart.list;

import com.arsen.epam.internet.shop.repository.cart.specification.CartUserCancelledSpecification;
import com.arsen.epam.internet.shop.repository.cart.specification.CartUserSpecification;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/cart/cancelled")
public class CancelledListController  extends CartListController{


    @Override
    protected CartUserSpecification getSpecification(int userId) {
        return new CartUserCancelledSpecification(userId);
    }

    @Override
    protected String getType() {
        return "cancelled";
    }
}

