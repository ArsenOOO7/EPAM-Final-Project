package com.arsen.epam.internet.shop.web.controller.admin.users.cart;


import com.arsen.epam.internet.shop.repository.cart.specification.CartUserRegisteredSpecification;
import com.arsen.epam.internet.shop.repository.cart.specification.CartUserSpecification;
import jakarta.servlet.annotation.WebServlet;

/**
 * Admin user's registered carts show controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/admin/users/cart/carted/*")
public class AdminCartedListController extends AdminCartListController{
    @Override
    protected CartUserSpecification getSpecification(int userId) {
        return new CartUserRegisteredSpecification(userId);
    }

    @Override
    protected String getType() {
        return "carted";
    }
}
