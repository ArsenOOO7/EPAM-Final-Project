package com.arsen.epam.internet.shop.service.validation.bean;

import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.user.IUserRepository;
import com.arsen.epam.internet.shop.repository.user.specification.UserLoginSpecification;
import com.arsen.epam.internet.shop.service.utils.Utils;
import com.arsen.epam.internet.shop.service.validation.Emails;
import com.arsen.epam.internet.shop.service.validation.Passwords;
import com.arsen.epam.internet.shop.service.validation.error.Error;
import jakarta.servlet.http.HttpServletRequest;

/**
 * User forms validation
 *
 * @author Arsen Sydoruk
 */
public class UserValidation {

    /**
     * Validate register user form
     *
     * @param req from controller
     * @return string error
     *         or empty - no errors
     */
    public static String validateRegister(HttpServletRequest req){

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeat_password");

        String birthDate = req.getParameter("birth_date");

        if(name == null || name.isEmpty()){
            return Error.USER_NAME_EMPTY;
        }

        if(surname == null || surname.isEmpty()){
            return Error.USER_SURNAME_EMPTY;
        }

        if(birthDate == null || birthDate.isEmpty()){
            return Error.USER_BIRTHDAY_EMPTY;
        }

        if(login == null || login.isEmpty()){
            return Error.USER_LOGIN_EMPTY;
        }

        if(email == null || email.isEmpty()){
            return Error.USER_EMAIL_EMPTY;
        }

        if(!Emails.isValid(email)){
            return Error.USER_EMAIL_INVALID;
        }

        if(password == null || password.isEmpty()){
            return Error.USER_PASSWORD_EMPTY;
        }

        if(!Passwords.isValid(password)){
            return Error.USER_PASSWORD_INVALID;
        }

        if(repeatPassword == null || repeatPassword.isEmpty()){
            return Error.USER_REPEAT_PASSWORD_EMPTY;
        }

        if(!Passwords.isEquals(password, repeatPassword)){
            return Error.USER_PASSWORD_EQUALS;
        }

        UserLoginSpecification loginSpecification = new UserLoginSpecification();
        loginSpecification.setLogin(login);

        IUserRepository repository = HeadRepository.getUserRepository();
        User find = repository.findOne(loginSpecification);

        if(find != null){
            return Error.USER_LOGIN_EXISTS;
        }

        loginSpecification.clear();
        loginSpecification.setEmail(email);

        find = repository.findOne(loginSpecification);

        if(find != null){
            return Error.USER_EMAIL_EXISTS;
        }

        return Error.SUCCESS;
    }


    /**
     * Validate login user form
     *
     * @param req from controller
     * @return string error
     *         or empty - no errors
     */
    public static String validateLogin(HttpServletRequest req){
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if(login == null || login.isEmpty()){
            return Error.USER_LOGIN_EMPTY;
        }

        if(password == null || password.isEmpty()){
            return Error.USER_PASSWORD_EMPTY;
        }

        return Error.SUCCESS;
    }


    /**
     * Validate password change user form
     *
     * @param req from controller
     * @return string error
     *         or empty - no errors
     */
    public static String validatePasswordChange(HttpServletRequest req, User user){
        String oldPassword = req.getParameter("old_password");
        String newPassword = req.getParameter("new_password");
        String repeatPassword = req.getParameter("repeat_password");

        String realPassword = user.getPassword();

        if(oldPassword == null || oldPassword.isEmpty()){
            return Error.USER_OLD_PASSWORD_EMPTY;
        }

        if(newPassword == null || newPassword.isEmpty()){
            return Error.USER_PASSWORD_EMPTY;
        }

        if(!Passwords.isValid(newPassword)){
            return Error.USER_PASSWORD_INVALID;
        }

        if(repeatPassword == null || repeatPassword.isEmpty()){
            return Error.USER_REPEAT_PASSWORD_EMPTY;
        }

        if(!Passwords.isEquals(oldPassword, realPassword)){
            return Error.USER_OLD_PASSWORD_EQUALS;
        }

        if(!Passwords.isEquals(newPassword, repeatPassword)){
            return Error.USER_PASSWORD_EQUALS;
        }

        return Error.SUCCESS;
    }


    /**
     * Validate edit user form
     *
     * @param req from controller
     * @return string error
     *         or empty - no errors
     */
    public static String validateUserEdit(HttpServletRequest req){

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String birthDate = req.getParameter("birth_date");

        if(name == null || name.isEmpty()){
            return Error.USER_NAME_EMPTY;
        }

        if(surname == null || surname.isEmpty()){
            return Error.USER_SURNAME_EMPTY;
        }

        if(birthDate == null || birthDate.isEmpty()){
            return Error.USER_BIRTHDAY_EMPTY;
        }

        return Error.SUCCESS;
    }


    /**
     * Validate top up balance user form
     *
     * @param req from controller
     * @return string error
     *         or empty - no errors
     */
    public static String validateUserTopUp(HttpServletRequest req){

        String moneyString = req.getParameter("money");

        if(moneyString == null || moneyString.isEmpty()){
            return Error.USER_TOP_UP_EMPTY;
        }

        int money = 0;
        try{
            money = Utils.getInt(moneyString);
        }catch (NumberFormatException exception){
            return Error.USER_TOP_UP_INVALID;
        }

        if(money < 0){
            return Error.USER_TOP_UP_NEGATIVE;
        }

        return Error.SUCCESS;
    }
}
