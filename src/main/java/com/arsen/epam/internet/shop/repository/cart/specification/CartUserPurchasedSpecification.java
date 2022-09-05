package com.arsen.epam.internet.shop.repository.cart.specification;

/**
 * Cart user purchased specification
 * Implementation of CartUserSpecification
 *
 * Helps to find carts with status PURCHASED
 *
 * @author Arsen Sydoryk
 */
public class CartUserPurchasedSpecification extends CartUserSpecification {

    public CartUserPurchasedSpecification(int userId) {
        super(userId);
    }

    @Override
    public int getStatus() {
        return 2;
    }
}
