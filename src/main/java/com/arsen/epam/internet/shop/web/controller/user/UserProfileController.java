package com.arsen.epam.internet.shop.web.controller.user;

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
 * User profile controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet(urlPatterns = {"/user", "/profile"})
public class UserProfileController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(UserProfileController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug(LogMessage.CONTROLLER_GET);
        req.getRequestDispatcher(Views.USER_PROFILE).forward(req, resp);
        log.debug(LogMessage.CONTROLLER_FINISHED);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
