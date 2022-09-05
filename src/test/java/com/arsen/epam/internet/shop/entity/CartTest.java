package com.arsen.epam.internet.shop.entity;

import com.arsen.epam.internet.shop.entity.cart.Cart;
import com.arsen.epam.internet.shop.entity.cart.status.PurchaseStatus;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CartTest {

    @Test
    public void testCart(){

        Cart cart = new Cart();

        cart.setId(1);
        cart.setUserId(1);
        cart.setProductId(1);
        cart.setAmount(1);
        cart.setPrice(1);

        LocalDateTime time = LocalDateTime.now();
        cart.setDate(Timestamp.valueOf(time));
        cart.setStatus(PurchaseStatus.CART);

        //Test getters
        assertNotNull(cart);
        assertEquals(1, cart.getId());
        assertEquals(1, cart.getUserId());
        assertEquals(1, cart.getAmount());
        assertEquals(1, cart.getPrice());

        assertEquals(time, cart.getDate().toLocalDateTime());
        assertEquals(PurchaseStatus.CART, cart.getStatus());

        //Change status
        cart.setStatus(2);
        assertEquals(PurchaseStatus.PURCHASED, cart.getStatus());

    }


}
