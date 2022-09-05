package com.arsen.epam.internet.shop.service.generator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneratorTest {

    @Test
    public void defaultGeneratorTest(){

        assertEquals(40, Generator.getRandomString().length());

    }

    @ParameterizedTest
    @MethodSource("length")
    public void testLength(int expectedLength){

        assertEquals(expectedLength, Generator.getRandomString(expectedLength).length());

    }


    private static Stream<Arguments> length(){
        return Stream.of(

                Arguments.of(10),
                Arguments.of(20),
                Arguments.of(30),
                Arguments.of(100)

        );
    }


}
