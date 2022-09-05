package com.arsen.epam.internet.shop.service.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class PasswordValidationTest {


    @ParameterizedTest
    @MethodSource("passwordValidationParameters")
    public void testPasswordValidation(String password, boolean expected){

        assertEquals(expected, Passwords.isValid(password));

    }

    @ParameterizedTest
    @MethodSource("passwordsEqualsParameters")
    public void testPasswordValidation(String password, String repeatPassword, boolean expected){

        assertEquals(expected, Passwords.isEquals(password, repeatPassword));

    }

    private static Stream<Arguments> passwordValidationParameters(){
        return Stream.of(

                Arguments.of("", false),
                Arguments.of("a", false),
                Arguments.of("password", false),
                Arguments.of("mysecretpassword", false),
                Arguments.of("mysecretpassword123", false),
                Arguments.of("mysecretpassword123".toUpperCase(), false),
                Arguments.of("mysecretpassword123BBB", true)

        );
    }


    private static Stream<Arguments> passwordsEqualsParameters(){
        return Stream.of(

                Arguments.of("password1", "", false),
                Arguments.of("password1", "pass", false),
                Arguments.of("password1", "password1", true),
                Arguments.of("password1", "password1".toUpperCase(), false)
        );
    }

}
