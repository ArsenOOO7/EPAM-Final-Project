package com.arsen.epam.internet.shop.service.validation.id;

import jakarta.servlet.http.HttpServletRequest;


/**
 * Class that validates id from path or query line
 *
 * @author Arsen Sydoryk
 */
public class IdValidator {

    /**
     * Validate id from path
     *
     * @param req from controller
     * @return id
     */
    public static int getPathId(HttpServletRequest req){
        String pathInfo = req.getPathInfo();

        if(pathInfo == null || pathInfo.isEmpty()){
            return -1;
        }

        String id = pathInfo.replaceFirst("/", "");
        try{
            return Integer.parseInt(id);
        }catch (NumberFormatException exception){
            return -1;
        }
    }

    /**
     * Validate id from query
     *
     * @param req from controller
     * @return id
     */
    public static int getQueryId(HttpServletRequest req){
        String queryInfo = req.getQueryString();

        if(queryInfo == null || queryInfo.isEmpty()){
            return -1;
        }

        String id = queryInfo.replace("id=", "");
        try{
            return Integer.parseInt(id);
        }catch (NumberFormatException exception){
            return -1;
        }
    }



}
