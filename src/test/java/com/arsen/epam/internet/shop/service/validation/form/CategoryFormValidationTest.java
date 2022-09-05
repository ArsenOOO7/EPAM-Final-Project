package com.arsen.epam.internet.shop.service.validation.form;

import com.arsen.epam.internet.shop.service.validation.bean.CategoryValidation;
import com.arsen.epam.internet.shop.service.validation.error.Error;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryFormValidationTest {

    @Test
    public void testCategories(){

        HttpServletRequest req = mock(HttpServletRequest.class);

        when(req.getParameter("identifier")).thenReturn(null);
        assertEquals(Error.CATEGORY_IDENTIFIER_EMPTY, CategoryValidation.validateCategory(req));

        when(req.getParameter("identifier")).thenReturn("laptop");
        when(req.getParameter("locale_ua")).thenReturn("Laptop");
        when(req.getParameter("locale_en")).thenReturn("Ноутбук");

        assertEquals(Error.CATEGORY_LOCALE_UA_INVALID, CategoryValidation.validateCategory(req));
        when(req.getParameter("locale_ua")).thenReturn("Ноутбук");

        assertEquals(Error.CATEGORY_LOCALE_EN_INVALID, CategoryValidation.validateCategory(req));
        when(req.getParameter("locale_en")).thenReturn("Laptop");

        assertEquals(Error.SUCCESS, CategoryValidation.validateCategory(req));

    }

}
