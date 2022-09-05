package com.arsen.epam.internet.shop.service.validation.form;

import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.service.validation.bean.UserValidation;
import com.arsen.epam.internet.shop.service.validation.error.Error;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserFormValidationTest {
    
    private final HttpServletRequest req = mock(HttpServletRequest.class);

    @Test
    public void testUserMin(){
        
        when(req.getParameter("name")).thenReturn(null);

        assertEquals(Error.USER_NAME_EMPTY, UserValidation.validateRegister(req));
        assertEquals(Error.USER_LOGIN_EMPTY, UserValidation.validateLogin(req));

    }


    @Test
    public void testRegisterUserFull(){

        when(req.getParameter("name")).thenReturn("Arsen");
        when(req.getParameter("surname")).thenReturn("Sydoryk");
        when(req.getParameter("birth_date")).thenReturn("03-15-2004");
        when(req.getParameter("email")).thenReturn("arsen.sydoryk$gmail.com");

        assertEquals(Error.USER_LOGIN_EMPTY, UserValidation.validateRegister(req));

        when(req.getParameter("login")).thenReturn("ArsenOOO7");
        assertEquals(Error.USER_EMAIL_INVALID, UserValidation.validateRegister(req));

        when(req.getParameter("email")).thenReturn("arsen.sydoryk@gmail.com");
        when(req.getParameter("password")).thenReturn("123");

        assertEquals(Error.USER_PASSWORD_INVALID, UserValidation.validateRegister(req));
        when(req.getParameter("password")).thenReturn("mysecretpasswordABCD123");

        assertEquals(Error.USER_REPEAT_PASSWORD_EMPTY, UserValidation.validateRegister(req));
        when(req.getParameter("repeat_password")).thenReturn("mysecretpasswordABCD12");

        assertEquals(Error.USER_PASSWORD_EQUALS, UserValidation.validateRegister(req));

    }


    @Test
    public void testUserLogin(){
        
        when(req.getParameter("login")).thenReturn(null);

        assertEquals(Error.USER_LOGIN_EMPTY, UserValidation.validateLogin(req));

        when(req.getParameter("login")).thenReturn("ArsenOOO7");
        assertEquals(Error.USER_PASSWORD_EMPTY, UserValidation.validateLogin(req));

        when(req.getParameter("password")).thenReturn("password123");
        assertEquals(Error.SUCCESS, UserValidation.validateLogin(req));


    }



    @Test
    public void testUserEdit(){

        when(req.getParameter("name")).thenReturn("Arsen");
        assertEquals(Error.USER_SURNAME_EMPTY, UserValidation.validateUserEdit(req));

        when(req.getParameter("surname")).thenReturn("Sydoryk");
        assertEquals(Error.USER_BIRTHDAY_EMPTY, UserValidation.validateUserEdit(req));

        when(req.getParameter("birth_date")).thenReturn("03-15-2004");
        assertEquals(Error.SUCCESS, UserValidation.validateUserEdit(req));

    }


    @Test
    public void testPasswordChange(){

        User user = new User();
        user.setPassword("mysecretpasswordABC123");

        when(req.getParameter("old_password")).thenReturn(null);
        assertEquals(Error.USER_OLD_PASSWORD_EMPTY, UserValidation.validatePasswordChange(req, user));

        when(req.getParameter("old_password")).thenReturn("mypass");
        assertEquals(Error.USER_PASSWORD_EMPTY, UserValidation.validatePasswordChange(req, user));

        when(req.getParameter("new_password")).thenReturn("123");
        assertEquals(Error.USER_PASSWORD_INVALID, UserValidation.validatePasswordChange(req, user));

        when(req.getParameter("new_password")).thenReturn("mysecretpasswordABC123456");
        assertEquals(Error.USER_REPEAT_PASSWORD_EMPTY, UserValidation.validatePasswordChange(req, user));

        when(req.getParameter("repeat_password")).thenReturn("1234");
        assertEquals(Error.USER_OLD_PASSWORD_EQUALS, UserValidation.validatePasswordChange(req, user));

        when(req.getParameter("old_password")).thenReturn("mysecretpasswordABC123");
        assertEquals(Error.USER_PASSWORD_EQUALS, UserValidation.validatePasswordChange(req, user));

        when(req.getParameter("repeat_password")).thenReturn("mysecretpasswordABC123456");
        assertEquals(Error.SUCCESS, UserValidation.validatePasswordChange(req, user));

    }


    @Test
    public void testTopUp(){

        
        when(req.getParameter("money")).thenReturn(null);

        assertEquals(Error.USER_TOP_UP_EMPTY, UserValidation.validateUserTopUp(req));

        when(req.getParameter("money")).thenReturn("aasljndiksdm");
        assertEquals(Error.USER_TOP_UP_INVALID, UserValidation.validateUserTopUp(req));

        when(req.getParameter("money")).thenReturn("-1");
        assertEquals(Error.USER_TOP_UP_NEGATIVE, UserValidation.validateUserTopUp(req));

        when(req.getParameter("money")).thenReturn("1");
        assertEquals(Error.SUCCESS, UserValidation.validateUserTopUp(req));

    }
    
}
