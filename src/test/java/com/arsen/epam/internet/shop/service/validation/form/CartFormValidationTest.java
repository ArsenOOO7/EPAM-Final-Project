package com.arsen.epam.internet.shop.service.validation.form;

import com.arsen.epam.internet.shop.service.validation.bean.CartValidation;
import com.arsen.epam.internet.shop.service.validation.error.Error;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartFormValidationTest {

    @Test
    public void testCategories(){

        HttpServletRequest req = mock(HttpServletRequest.class);

        when(req.getParameter("amount")).thenReturn(null);
        assertEquals(Error.CART_AMOUNT_EMPTY, CartValidation.addProductValidate(req));

        when(req.getParameter("amount")).thenReturn("jsaidhjasidj");
        assertEquals(Error.CART_AMOUNT_INVALID, CartValidation.addProductValidate(req));

        when(req.getParameter("amount")).thenReturn("-1");
        assertEquals(Error.CART_AMOUNT_NEGATIVE, CartValidation.addProductValidate(req));

        when(req.getParameter("amount")).thenReturn("1");
        assertEquals(Error.SUCCESS, CartValidation.addProductValidate(req));

    }

}
