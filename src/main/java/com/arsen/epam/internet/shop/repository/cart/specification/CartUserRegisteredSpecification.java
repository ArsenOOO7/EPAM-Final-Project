package com.arsen.epam.internet.shop.repository.cart.specification;

/**
 * Cart user registered specification
 * Implementation of CartUserSpecification
 *
 * Helps to find carts with status CARTED
 *
 * @author Arsen Sydoryk
 */
public class CartUserRegisteredSpecification extends CartUserSpecification {


    public CartUserRegisteredSpecification(int userId) {
        super(userId);
    }


    @Override
    public int getStatus() {
        return 1;
    }
}
