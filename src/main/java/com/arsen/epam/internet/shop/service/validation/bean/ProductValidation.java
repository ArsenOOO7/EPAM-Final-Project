package com.arsen.epam.internet.shop.service.validation.bean;

import com.arsen.epam.internet.shop.service.utils.Utils;
import com.arsen.epam.internet.shop.service.validation.error.Error;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Product forms validation
 *
 * @author Arsen Sydoruk
 */
public class ProductValidation {

    /**
     * Validate add & edit product forms
     *
     * @param req from controller
     * @return error object
     */
    public static String validateProduct(HttpServletRequest req){

        String shortName = req.getParameter("shortname");
        String fullName = req.getParameter("fullname");
        String description = req.getParameter("description");

        String amountValue = req.getParameter("amount");
        String priceValue = req.getParameter("price");

        String colorValue = req.getParameter("color");
        String sizeUnitValue = req.getParameter("size_unit");
        String sizeValue = req.getParameter("size_value");

        String categoryValue = req.getParameter("category");

        if(shortName == null || shortName.isEmpty()){
            return Error.PRODUCT_SHORTNAME_EMPTY;
        }

        if(fullName == null || fullName.isEmpty()){
            return Error.PRODUCT_FULL_NAME_EMPTY;
        }

        if(description == null || description.isEmpty()){
            return Error.PRODUCT_DESCRIPTION_EMPTY;
        }

        if(amountValue == null || amountValue.isEmpty()){
            return Error.PRODUCT_AMOUNT_EMPTY;
        }

        if(priceValue == null || priceValue.isEmpty()){
            return Error.PRODUCT_PRICE_EMPTY;
        }

        if(colorValue == null || colorValue.isEmpty()){
            return Error.PRODUCT_COLOR_EMPTY;
        }

        if(sizeUnitValue == null || sizeUnitValue.isEmpty()){
            return Error.PRODUCT_SIZE_UNIT_EMPTY;
        }

        if(sizeValue == null || sizeValue.isEmpty()){
            return Error.PRODUCT_SIZE_VALUE_EMPTY;
        }

        if(categoryValue == null || categoryValue.isEmpty()){
            return Error.PRODUCT_CATEGORY_EMPTY;
        }

        try{
            Integer.parseInt(amountValue);
        }catch (NumberFormatException exception){
            return Error.PRODUCT_AMOUNT_INVALID;
        }

        try{
            Double.parseDouble(priceValue);
        }catch (NumberFormatException exception){
            return Error.PRODUCT_PRICE_INVALID;
        }

        try{
            Integer.parseInt(sizeValue);
        }catch (NumberFormatException exception){
            return Error.PRODUCT_SIZE_VALUE_INVALID;
        }

        try{
            Utils.getCategoryId(categoryValue);
        }catch (NumberFormatException exception){
            return Error.PRODUCT_CATEGORY_INVALID;
        }

        return Error.SUCCESS;
    }

}
