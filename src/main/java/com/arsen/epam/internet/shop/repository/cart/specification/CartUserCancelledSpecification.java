package com.arsen.epam.internet.shop.repository.cart.specification;


/**
 * Cart user cancelled specification
 * Implementation of CartUserSpecification
 *
 * Helps to find carts with status CANCELLED
 *
 * @author Arsen Sydoryk
 */
public class CartUserCancelledSpecification extends CartUserSpecification {

    public CartUserCancelledSpecification(int userId) {
        super(userId);
    }

    @Override
    public int getStatus() {
        return 3;
    }
}
