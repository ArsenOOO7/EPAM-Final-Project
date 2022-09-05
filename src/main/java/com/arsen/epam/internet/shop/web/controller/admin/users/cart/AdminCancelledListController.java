package com.arsen.epam.internet.shop.web.controller.admin.users.cart;

import com.arsen.epam.internet.shop.repository.cart.specification.CartUserCancelledSpecification;
import com.arsen.epam.internet.shop.repository.cart.specification.CartUserSpecification;
import jakarta.servlet.annotation.WebServlet;

/**
 * Admin user's cancelled carts show controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/admin/users/cart/cancelled/*")
public class AdminCancelledListController  extends AdminCartListController{
    @Override
    protected CartUserSpecification getSpecification(int userId) {
        return new CartUserCancelledSpecification(userId);
    }

    @Override
    protected String getType() {
        return "cancelled";
    }
}
