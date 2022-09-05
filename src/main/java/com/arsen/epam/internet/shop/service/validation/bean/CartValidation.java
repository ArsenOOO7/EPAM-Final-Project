package com.arsen.epam.internet.shop.service.validation.bean;

import com.arsen.epam.internet.shop.service.validation.error.Error;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Cart form validation
 *
 * @author Arsen Sydoruk
 */
public class CartValidation {

    /**
     * Validate add product to cart form
     *
     * @param req from controller
     * @return string error
     *         or empty - no errors
     */
    public static String addProductValidate(HttpServletRequest req){

        String amountStr = req.getParameter("amount");
        if(amountStr == null || amountStr.isEmpty()){
            return Error.CART_AMOUNT_EMPTY;
        }

        int amount = 0;
        try{
            amount = Integer.parseInt(amountStr);
        }catch (NumberFormatException exception){
            return Error.CART_AMOUNT_INVALID;
        }

        if(amount <= 0){
            return Error.CART_AMOUNT_NEGATIVE;
        }

        return "";

    }

}
