package com.arsen.epam.internet.shop.service.generator;

import java.util.Random;

/**
 * Generator clas
 * Generates some string
 *
 * @author Arsen Sydoryk
 */
public class Generator {


    public static String getRandomString(){
        return getRandomString(40);
    }

    /**
     * Generates string with specific length
     *
     * @param length of string
     * @return generated string
     */
    public static String getRandomString(int length){

        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < length; ++i){
            builder.append(letters.charAt(random.nextInt(letters.length())));
        }

        return builder.toString();
    }

}
