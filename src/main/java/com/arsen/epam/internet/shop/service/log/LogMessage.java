package com.arsen.epam.internet.shop.service.log;


/**
 * Class with ofter used logger messages
 *
 * @author Arsen Sydoryk
 */
public class LogMessage {

    public static final String CONTROLLER_START = "Controller started";
    public static final String CONTROLLER_FINISHED = "Controller finished";

    public static final String CONTROLLER_GET = CONTROLLER_START + ". Method: GET";
    public static final String CONTROLLER_POST = CONTROLLER_START + ". Method: POST";

    public static final String EXTRACTING_ID = "Extracting id...";
    public static final String EXTRACTING_PAGE = "Extracting number of page...";

    public static final String ERROR = "Error: ";
    public static final String ERROR_CODE = "Error code: ";

    public static final String USER_SESSION = "Getting user from SessionScope...";



}
