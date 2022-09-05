package com.arsen.epam.internet.shop.web.controller.user;

import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.service.validation.bean.UserValidation;
import com.arsen.epam.internet.shop.web.path.Views;
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
 * User password change controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/user/edit/password")
public class UserPasswordChangeController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(UserPasswordChangeController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug(LogMessage.CONTROLLER_GET);
        req.getRequestDispatcher(Views.USER_PASSWORD_CHANGE).forward(req, resp);
        log.debug(LogMessage.CONTROLLER_FINISHED);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_POST);
        log.debug(LogMessage.USER_SESSION);
        User user = (User) req.getSession().getAttribute("user");

        String message = UserValidation.validatePasswordChange(req, user);
        if(!message.isEmpty()){
            req.getSession().setAttribute("password_change_message", message);
            log.error(message);
            resp.sendRedirect(WebPath.USER_PASSWORD_CHANGE);
            return;
        }

        String newPassword = req.getParameter("new_password");

        log.trace("Setting a new password...");
        user.setPassword(newPassword);


        HeadRepository.getUserRepository().update(user);

        log.debug(LogMessage.CONTROLLER_FINISHED);
        resp.sendRedirect(WebPath.USER_PROFILE);

    }
}
