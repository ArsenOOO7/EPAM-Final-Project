package com.arsen.epam.internet.shop.service.validation.form;

import com.arsen.epam.internet.shop.service.validation.bean.ProductValidation;
import com.arsen.epam.internet.shop.service.validation.error.Error;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductFormValidationTest {


    @Test
    public void testProductAddAndEdit(){

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("shortname")).thenReturn("Test product");
        when(req.getParameter("fullname")).thenReturn("Test product full name");
        when(req.getParameter("description")).thenReturn("Some description about product");

        assertEquals(Error.PRODUCT_AMOUNT_EMPTY, ProductValidation.validateProduct(req));

        when(req.getParameter("amount")).thenReturn("asmdksjad");
        when(req.getParameter("price")).thenReturn("asmdksjad");
        when(req.getParameter("size_value")).thenReturn("10");
        when(req.getParameter("category")).thenReturn("category_");

        when(req.getParameter("color")).thenReturn("black");
        when(req.getParameter("size_unit")).thenReturn("m");

        assertEquals(Error.PRODUCT_AMOUNT_INVALID, ProductValidation.validateProduct(req));
        when(req.getParameter("amount")).thenReturn("2");

        assertEquals(Error.PRODUCT_PRICE_INVALID, ProductValidation.validateProduct(req));
        when(req.getParameter("price")).thenReturn("10");

        assertEquals(Error.PRODUCT_CATEGORY_INVALID, ProductValidation.validateProduct(req));
        when(req.getParameter("category")).thenReturn("category_1");

        assertEquals(Error.SUCCESS, ProductValidation.validateProduct(req));

    }

}
