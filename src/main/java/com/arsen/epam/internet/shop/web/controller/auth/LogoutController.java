package com.arsen.epam.internet.shop.web.controller.auth;

import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.web.path.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Logout controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/logout")
public class LogoutController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(LogoutController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug(LogMessage.CONTROLLER_GET);
        if(req.getSession().getAttribute("user") != null){

            User user = (User) req.getSession().getAttribute("user");
            HeadRepository.getUserRepository().update(user);
            log.info("User " + user.getId() + " logged out");
            req.getSession().removeAttribute("user");

        }else{

            log.error("User is not signed in");

        }

        log.debug(LogMessage.CONTROLLER_FINISHED);
        resp.sendRedirect(WebPath.HOME);
    }
}
