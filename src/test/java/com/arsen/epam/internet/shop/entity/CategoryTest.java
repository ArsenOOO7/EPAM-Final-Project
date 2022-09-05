package com.arsen.epam.internet.shop.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CategoryTest {

    @Test
    public void testCategory(){

        Category category = new Category();

        category.setId(1);
        category.setIdentifier("laptop");
        category.setLocaleUA("Ноутбуки");
        category.setLocaleEN("Laptops");

        //Test getters
        assertNotNull(category);

        assertEquals(1, category.getId());
        assertEquals("laptop", category.getIdentifier());
        assertEquals("Ноутбуки", category.getLocaleUA());
        assertEquals("Laptops", category.getLocaleEN());

    }

}
