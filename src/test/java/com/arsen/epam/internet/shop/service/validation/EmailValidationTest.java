package com.arsen.epam.internet.shop.service.validation;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailValidationTest {


    @ParameterizedTest
    @MethodSource("emailValidationParameters")
    public void testEmailValidation(String email, boolean excepted){

        assertEquals(excepted, Emails.isValid(email));

    }


    private static Stream<Arguments> emailValidationParameters(){
        return Stream.of(

                Arguments.of("", false),
                Arguments.of("@", false),
                Arguments.of("arsen", false),
                Arguments.of("arsen@mail", false),
                Arguments.of("mail.com", false),
                Arguments.of("arsen.mail.com", false),
                Arguments.of("arsen@mail.com", true),
                Arguments.of("arsen.sydoryk@gmail.com", true)

        );
    }

}
