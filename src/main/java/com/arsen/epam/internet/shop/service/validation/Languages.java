package com.arsen.epam.internet.shop.service.validation;

/**
 * Class with language validation
 *
 * @author Arsen Sydoryk
 */
public class Languages {

    public final static String ENGLISH_PATTERN = "^[A-Za-z\s]+$";
    public final static String UKRAINIAN_PATTERN = "^[А-Яа-яІіЄєҐґЇї\s]+$";

    /**
     * Check if it's English
     *
     * @param value string
     * @return booean
     */
    public static boolean isEnglish(String value){
        return value.matches(ENGLISH_PATTERN);
    }


    /**
     * Check if it's Ukrainian
     *
     * @param value string
     * @return booean
     */
    public static boolean isUkrainian(String value){
        return value.matches(UKRAINIAN_PATTERN);
    }

}
