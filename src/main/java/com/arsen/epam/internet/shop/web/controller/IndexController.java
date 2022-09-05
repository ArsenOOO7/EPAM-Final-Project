package com.arsen.epam.internet.shop.web.controller;

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
 * Index controller
 * Home controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet(urlPatterns = {"", "/index", "/home"})
public class IndexController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(IndexController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug(LogMessage.CONTROLLER_GET);
        req.getRequestDispatcher(Views.HOME_VIEW).forward(req, resp);
        log.debug(LogMessage.CONTROLLER_FINISHED);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug(LogMessage.CONTROLLER_POST);
        req.getRequestDispatcher(Views.HOME_VIEW).forward(req, resp);
        log.debug(LogMessage.CONTROLLER_FINISHED);
    }
}

