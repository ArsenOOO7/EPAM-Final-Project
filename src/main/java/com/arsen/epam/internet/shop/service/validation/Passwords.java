package com.arsen.epam.internet.shop.service.validation;

/**
 * Class that validates passwords
 *
 * @author Arsen Sydoryk
 */
public class Passwords {

    public final static String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

    /**
     * Check if password is valid
     *
     * @param password from form
     * @return boolean
     */
    public static boolean isValid(String password){
        return password.matches(PASSWORD_REGEX);
    }

    /**
     * Check if passwords are equals
     *
     * @param password from form
     * @param repeatPassword from form
     * @return boolean
     */
    public static boolean isEquals(String password, String repeatPassword){
        return password.equals(repeatPassword);
    }

}
