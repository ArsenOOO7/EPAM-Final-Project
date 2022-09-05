package com.arsen.epam.internet.shop.web.path;

/**
 * Class with web path
 *
 * @author Arsen Sydoryk
 */
public class WebPath {

    public final static String WEB = "/";
    public final static String PATH = WEB + "shop/";


    public final static String HOME = PATH;

    public final static String LOGIN = PATH + "login";
    public final static String REGISTER = PATH + "register";

    public final static String USER_PROFILE = PATH + "profile";
    public final static String USER_EDIT = PATH + "user/edit";
    public final static String USER_TOP_UP = PATH + "user/topup";
    public final static String USER_PASSWORD_CHANGE = PATH + "user/edit/password";

    public final static String PRODUCT_SEARCH = PATH + "product";
    public final static String PRODUCT_ADD = PATH + "product/add";
    public final static String PRODUCT_SHOW = PATH + "product/show/";
    public final static String PRODUCT_EDIT = PATH + "product/edit/";

    public final static String CATEGORIES = PATH + "category";
    public final static String CATEGORIES_ADD = PATH + "category/add";

    public final static String CART = PATH + "cart";
    public final static String CART_CARTED = PATH + "cart/carted";
    public final static String CART_PURCHASED = PATH + "cart/purchased";
    public final static String CART_CANCELLED = PATH + "cart/cancelled";

    public final static String ADMIN = PATH + "admin";
    public final static String ADMIN_USERS = PATH + "admin/users";
    public final static String ADMIN_USER_SHOW = PATH + "admin/users/show/";
    public final static String ADMIN_USER_CART_LIST = PATH + "admin/users/cart/";
    public final static String ADMIN_USER_CART_LIST_CARTED = PATH + "admin/users/cart/carted/";
    public final static String ADMIN_USER_CART_LIST_PURCHASED = PATH + "admin/users/cart/purchased/";
    public final static String ADMIN_USER_CART_LIST_CANCELLED = PATH + "admin/users/cart/cancelled/";





}
