package com.arsen.epam.internet.shop.service.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class with different methods
 *
 * @author Arsen Sydoryk
 */
public class Utils {

    /**
     * String -> int
     *
     * @param amountValue string
     * @return int
     */
    public static int getInt(String amountValue){
        return Integer.parseInt(amountValue);
    }


    /**
     * Extract id from category data from forms
     *
     * @param categoryValue format: category_{id}
     * @return id ({id})
     * @throws NumberFormatException
     *  if it's not integer
     */
    public static int getCategoryId(String categoryValue) throws NumberFormatException{

        String patternValue = "^category_((\\d*))$";
        Pattern pattern = Pattern.compile(patternValue);
        Matcher matcher = pattern.matcher(categoryValue);

        if(matcher.find()){

            return Integer.parseInt(matcher.group(1));

        }

        return 0;
    }


    /**
     * Seconds to date
     *
     * @param seconds time
     * @return string date
     */
    public static String secondsToDatetime(long seconds){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss MM-dd-yyyy");
        return dateFormat.format(new Date(seconds * 1000L));
    }

    /**
     * Build query line
     *
     * @param req from controller
     * @return string line
     */
    public static String buildQueryLine(HttpServletRequest req){

        Enumeration<String> keys = req.getParameterNames();
        List<String> lines = new ArrayList<>();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            if(!key.equalsIgnoreCase("page")){
                lines.add(key + "=" + req.getParameter(key));
            }
        }

        return String.join("&", lines);
    }


    /**
     *
     * Get current time in seconds
     * To avoid dependence on a mysql database
     * Analogue of UNIX_TIMESTAMP() (MySQL & MariaDB)
     *
     * @return int
     */
    public static int getUnixTimestamp(){
        return (int) System.currentTimeMillis() * 1000;
    }
}
