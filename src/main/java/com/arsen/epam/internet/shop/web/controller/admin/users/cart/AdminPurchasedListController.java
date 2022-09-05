package com.arsen.epam.internet.shop.web.controller.admin.users.cart;

import com.arsen.epam.internet.shop.repository.cart.specification.CartUserPurchasedSpecification;
import com.arsen.epam.internet.shop.repository.cart.specification.CartUserSpecification;
import jakarta.servlet.annotation.WebServlet;

/**
 * Admin user's purchased carts show controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/admin/users/cart/purchased/*")
public class AdminPurchasedListController extends AdminCartListController{

    @Override
    protected CartUserSpecification getSpecification(int userId) {
        return new CartUserPurchasedSpecification(userId);
    }

    @Override
    protected String getType() {
        return "purchased";
    }
}
