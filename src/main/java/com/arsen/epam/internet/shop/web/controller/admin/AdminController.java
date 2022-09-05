package com.arsen.epam.internet.shop.web.controller.admin;

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
 * Show admin links controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/admin")
public class AdminController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(AdminController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug(LogMessage.CONTROLLER_GET);
        req.getRequestDispatcher(Views.ADMIN).forward(req, resp);
        log.debug(LogMessage.CONTROLLER_FINISHED);
    }
}
