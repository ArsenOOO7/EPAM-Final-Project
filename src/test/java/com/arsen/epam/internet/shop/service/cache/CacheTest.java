package com.arsen.epam.internet.shop.service.cache;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CacheTest {

    private final Cache cache = Cache.getInstance();

    @ParameterizedTest
    @MethodSource("cachingParametrizedTest")
    public void testCaching(String key, Object expected){

        cache.setAttribute(key, expected);

        assertTrue(cache.hasAttribute(key));
        Object attributeValue = cache.getAttribute(key);

        assertNotNull(attributeValue);
        assertEquals(expected, attributeValue);

    }

    @Test
    public void testEmpty(){

        String key = "surname";
        assertFalse(cache.hasAttribute(key));
        assertNull(cache.getAttribute(key));

    }


    private static Stream<Arguments> cachingParametrizedTest(){
        return Stream.of(

                Arguments.of("id", 0),
                Arguments.of("name", "Name"),
                Arguments.of("categories", List.of(1, 2, 3, 4))

        );
    }

}
