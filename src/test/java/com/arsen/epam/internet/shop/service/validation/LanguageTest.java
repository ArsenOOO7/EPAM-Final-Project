package com.arsen.epam.internet.shop.service.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class LanguageTest {

    @ParameterizedTest
    @MethodSource("languageUaParameters")
    public void testUkrainian(String message, boolean expected){

        assertEquals(expected, Languages.isUkrainian(message));

    }

    @ParameterizedTest
    @MethodSource("languageEnParameters")
    public void testEnglish(String message, boolean expected){

        assertEquals(expected, Languages.isEnglish(message));

    }




    private static Stream<Arguments> languageUaParameters(){
        return Stream.of(

                Arguments.of("Привіт", true),
                Arguments.of("Як справи", true),
                Arguments.of("Українська мова", true),
                Arguments.of("Аrсен", false),
                Arguments.of("English", false),
                Arguments.of("Deutschland", false),
                Arguments.of("My name is Arsen", false)

        );
    }

    private static Stream<Arguments> languageEnParameters(){
        return Stream.of(

                Arguments.of("Hello", true),
                Arguments.of("Laptop", true),
                Arguments.of("Dishes", true),
                Arguments.of("My name is Arsen", true),
                Arguments.of("My name is Aрsen", false),
                Arguments.of("Ноутбук", false),
                Arguments.of("Смартфон", false)
        );
    }

}
