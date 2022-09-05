package com.arsen.epam.internet.shop.entity;


import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.entity.user.status.UserStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUser(){

        User user = new User();
        user.setId(1);
        user.setName("Arsen");
        user.setSurname("Sydoryk");
        user.setEmail("arsen.sydoryk@gmail.com");
        user.setLogin("ArsenOOO7");
        user.setPassword("testpass");
        user.setBalance(0.0);
        user.setAvatarId(0);
        user.setStatus(UserStatus.USER);

        //Test getters
        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("Arsen", user.getName());
        assertEquals("Sydoryk", user.getSurname());
        assertEquals("arsen.sydoryk@gmail.com", user.getEmail());
        assertEquals("ArsenOOO7", user.getLogin());
        assertEquals("testpass", user.getPassword());
        assertEquals(0.0, user.getBalance());
        assertEquals(0, user.getAvatarId());
        assertEquals(UserStatus.USER, user.getStatus());

        //Test balance
        assertFalse(user.hasEnoughMoney(100));

        user.increaseBalance(200);
        assertTrue(user.hasEnoughMoney(100));

        user.decreaseBalance(100);
        assertTrue(user.hasEnoughMoney(100));
        assertEquals(100, user.getBalance());

        //Ban
        assertFalse(user.isBanned());

    }

}
