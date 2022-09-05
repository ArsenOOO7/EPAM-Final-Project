package com.arsen.epam.internet.shop.service.validation.error;


/**
 * Class with error codes
 *
 * @author Arsen Sydoryk
 */
public class Error {

    public static final String SUCCESS = "";

    //Users
    public static final String USER_NAME_EMPTY = "user.invalid.input.name";
    public static final String USER_SURNAME_EMPTY = "user.invalid.input.surname";
    public static final String USER_BIRTHDAY_EMPTY = "user.invalid.input.birthdate";
    public static final String USER_LOGIN_EMPTY = "user.invalid.input.login";

    public static final String USER_EMAIL_EMPTY = "user.invalid.input.email";
    public static final String USER_EMAIL_INVALID = "user.invalid.input.email.pattern";

    public static final String USER_PASSWORD_EMPTY = "user.invalid.input.password";
    public static final String USER_PASSWORD_INVALID = "user.invalid.input.password.pattern";
    public static final String USER_REPEAT_PASSWORD_EMPTY = "user.invalid.input.repeat.password";
    public static final String USER_PASSWORD_EQUALS = "user.invalid.input.passwords.equals";

    public static final String USER_OLD_PASSWORD_EMPTY = "password.change.invalid.input.old";
    public static final String USER_OLD_PASSWORD_EQUALS = "password.change.invalid.input.old.equals";

    public static final String USER_LOGIN_EXISTS = "user.invalid.input.login.exists";
    public static final String USER_EMAIL_EXISTS = "user.invalid.input.email.exists";

    public static final String USER_TOP_UP_EMPTY = "top.up.invalid.input.money";
    public static final String USER_TOP_UP_INVALID = "top.up.invalid.input.money.value";
    public static final String USER_TOP_UP_NEGATIVE = "top.up.invalid.input.money.value.negative";

    //products
    public static final String PRODUCT_SHORTNAME_EMPTY = "product.invalid.input.shortname";
    public static final String PRODUCT_FULL_NAME_EMPTY = "product.invalid.input.fullname";
    public static final String PRODUCT_DESCRIPTION_EMPTY = "product.invalid.input.description";

    public static final String PRODUCT_AMOUNT_EMPTY = "product.invalid.input.amount";
    public static final String PRODUCT_AMOUNT_INVALID = "product.invalid.input.amount.value";

    public static final String PRODUCT_PRICE_EMPTY = "product.invalid.input.price";
    public static final String PRODUCT_PRICE_INVALID = "product.invalid.input.price.value";

    public static final String PRODUCT_COLOR_EMPTY = "product.invalid.input.color";
    public static final String PRODUCT_SIZE_UNIT_EMPTY = "product.invalid.input.size.unit";

    public static final String PRODUCT_SIZE_VALUE_EMPTY = "product.invalid.input.size.value";
    public static final String PRODUCT_SIZE_VALUE_INVALID = "product.invalid.input.size.value.numeric";

    public static final String PRODUCT_CATEGORY_EMPTY = "product.invalid.input.category";
    public static final String PRODUCT_CATEGORY_INVALID = "product.invalid.input.category.value";

    //categories
    public static final String CATEGORY_IDENTIFIER_EMPTY = "category.invalid.input.identifier";
    public static final String CATEGORY_LOCALE_UA_EMPTY = "category.invalid.input.locale.ua";
    public static final String CATEGORY_LOCALE_EN_EMPTY = "category.invalid.input.locale.en";

    public static final String CATEGORY_LOCALE_UA_INVALID = "category.invalid.input.locale.ua.value";
    public static final String CATEGORY_LOCALE_EN_INVALID = "category.invalid.input.locale.en.value";


    //cart
    public static final String CART_AMOUNT_EMPTY = "cart.product.add.invalid.amount";
    public static final String CART_AMOUNT_INVALID = "cart.product.add.invalid.amount.value";
    public static final String CART_AMOUNT_NEGATIVE = "cart.product.add.invalid.amount.negative";


}
