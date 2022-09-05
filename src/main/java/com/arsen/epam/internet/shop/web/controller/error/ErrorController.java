package com.arsen.epam.internet.shop.web.controller.error;


import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.web.path.Views;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 *
 * Error page
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/error")
public class ErrorController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ErrorController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug(LogMessage.CONTROLLER_GET);
        process(req, resp);
        log.debug(LogMessage.CONTROLLER_FINISHED);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug(LogMessage.CONTROLLER_POST);
        process(req, resp);
        log.debug(LogMessage.CONTROLLER_FINISHED);
    }


    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.trace("Status code: " + req.getAttribute("jakarta.servlet.error.status_code"));
        log.trace("Message: " + req.getAttribute("jakarta.servlet.error.message"));
        log.trace("Request uri: " + req.getAttribute("jakarta.servlet.error.request_uri"));

        req.getRequestDispatcher(Views.ERROR_PAGE).forward(req, resp);
    }

}
