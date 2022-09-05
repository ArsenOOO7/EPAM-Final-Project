package com.arsen.epam.internet.shop.web.controller.cart.list;

import com.arsen.epam.internet.shop.repository.cart.specification.CartUserPurchasedSpecification;
import com.arsen.epam.internet.shop.repository.cart.specification.CartUserSpecification;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/cart/purchased")
public class PurchasedListController  extends CartListController{


    @Override
    protected CartUserSpecification getSpecification(int userId) {
        return new CartUserPurchasedSpecification(userId);
    }

    @Override
    protected String getType() {
        return "purchased";
    }
}
