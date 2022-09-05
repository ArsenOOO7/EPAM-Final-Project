package com.arsen.epam.internet.shop.service.validation;

/**
 * Class with email validation
 *
 * @author Arsen Sydoryk
 */
public class Emails {

    public final static String EMAIL_PATTERN = "^(\\w+)(\\.\\w*)?@(\\w*)\\.(\\w{2,5})$";

    /**
     * Validate email
     *
     * @param email from form
     * @return boolean
     */
    public static boolean isValid(String email){

        return email.matches(EMAIL_PATTERN);

    }

}
