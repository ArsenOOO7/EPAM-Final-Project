package com.arsen.epam.internet.shop.service.validation.bean;

import com.arsen.epam.internet.shop.service.validation.Languages;
import com.arsen.epam.internet.shop.service.validation.error.Error;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Category forms validation
 *
 * @author Arsen Sydoruk
 */
public class CategoryValidation {

    /**
     * Validate add & edit category forms
     *
     * @param req from controller
     * @return string error
     *         or empty - no errors
     */
    public static String validateCategory(HttpServletRequest req){
        String identifier = req.getParameter("identifier");
        String localeUA = req.getParameter("locale_ua");
        String localeEN = req.getParameter("locale_en");

        if(identifier == null || identifier.isEmpty()){
            return Error.CATEGORY_IDENTIFIER_EMPTY;
        }

        if(localeUA == null || localeUA.isEmpty()){
            return Error.CATEGORY_LOCALE_UA_EMPTY;
        }

        if(localeEN == null || localeEN.isEmpty()){
            return Error.CATEGORY_LOCALE_EN_EMPTY;
        }

        if(!Languages.isUkrainian(localeUA)){
            return Error.CATEGORY_LOCALE_UA_INVALID;
        }

        if(!Languages.isEnglish(localeEN)){
            return Error.CATEGORY_LOCALE_EN_INVALID;
        }

        return Error.SUCCESS;
    }

}
