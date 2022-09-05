package com.arsen.epam.internet.shop.entity;

import com.arsen.epam.internet.shop.entity.product.Product;
import com.arsen.epam.internet.shop.entity.product.color.Color;
import com.arsen.epam.internet.shop.entity.product.size.SizeUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductTest {

    @Test
    public void testProduct(){

        Product product = new Product();
        product.setId(0);
        product.setShortName("Shortname");
        product.setFullName("Fullname");
        product.setDescription("Description");

        product.setAmount(100);
        product.setPrice(1000);
        product.setSizeUnit(SizeUnit.CENTIMETRE);
        product.setSizeValue(100);
        product.setColor(Color.RED);

        product.setPreviewImageId(0);
        product.setCategoryId(0);
        product.setStartDate(0);
        product.setBoughtCounter(0);

        //Test getters...
        assertNotNull(product);

        assertEquals(0, product.getId());
        assertEquals("Shortname", product.getShortName());
        assertEquals("Fullname", product.getFullName());
        assertEquals("Description", product.getDescription());

        assertEquals(100, product.getAmount());
        assertEquals(1000, product.getPrice());
        assertEquals(SizeUnit.CENTIMETRE, product.getSizeUnit());
        assertEquals(100, product.getSizeValue());
        assertEquals(Color.RED, product.getColor());

        assertEquals(0, product.getPreviewImageId());
        assertEquals(0, product.getCategoryId());
        assertEquals(0, product.getStartDate());
        assertEquals(0, product.getBoughtCounter());

        //Change color
        product.setColor(Color.BLACK);
        assertEquals(Color.BLACK, product.getColor());

        //Change size unit
        product.setSizeUnit(SizeUnit.METRE);
        assertEquals(SizeUnit.METRE, product.getSizeUnit());

    }

}
