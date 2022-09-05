package com.arsen.epam.internet.shop.service.validation.id;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IdValidationTest {

    private final HttpServletRequest req = mock(HttpServletRequest.class);

    @Test
    public void testIdPath(){

        when(req.getPathInfo()).thenReturn(null);
        Assertions.assertEquals(-1, IdValidator.getPathId(req));

        when(req.getPathInfo()).thenReturn("/aaa");
        Assertions.assertEquals(-1, IdValidator.getPathId(req));

        when(req.getPathInfo()).thenReturn("/1");
        Assertions.assertEquals(1, IdValidator.getPathId(req));

    }

    @Test
    public void testIdQuery(){

        when(req.getQueryString()).thenReturn(null);
        Assertions.assertEquals(-1, IdValidator.getQueryId(req));

        when(req.getQueryString()).thenReturn("id=aaa");
        Assertions.assertEquals(-1, IdValidator.getQueryId(req));

        when(req.getQueryString()).thenReturn("id=1");
        Assertions.assertEquals(1, IdValidator.getQueryId(req));

    }

}
