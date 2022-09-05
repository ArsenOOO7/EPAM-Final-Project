package com.arsen.epam.internet.shop.web.controller.auth;

import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.entity.user.ban.UserBan;
import com.arsen.epam.internet.shop.entity.user.status.UserStatus;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.user.IUserRepository;
import com.arsen.epam.internet.shop.repository.user.specification.UserLoginSpecification;
import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.service.utils.Utils;
import com.arsen.epam.internet.shop.service.validation.Emails;
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
 * Login controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(LoginController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug(LogMessage.CONTROLLER_GET);
        req.getRequestDispatcher(Views.LOGIN_VIEW).forward(req, resp);
        log.debug(LogMessage.CONTROLLER_FINISHED);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_POST);

        String message = UserValidation.validateLogin(req);
        if(!message.isEmpty()){
            req.getSession().setAttribute("login_error_message", message);
            log.error(LogMessage.ERROR_CODE + message);
            resp.sendRedirect(WebPath.REGISTER);
            return;
        }

        IUserRepository repository = HeadRepository.getUserRepository();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserLoginSpecification loginSpecification = new UserLoginSpecification();
        if(Emails.isValid(login)){
            loginSpecification.setEmail(login);
            log.trace("Email: " + login);
        }else{
            log.trace("Login: " + login);
            loginSpecification.setLogin(login);
        }

        loginSpecification.setPassword(password);
        User user = repository.findOne(loginSpecification);

        if(user == null){
            resp.sendRedirect(WebPath.LOGIN);
            req.getSession().setAttribute("login_error_message", "user.input.login.undefined");
            log.error(LogMessage.ERROR_CODE + "user.input.login.undefined");
            return;
        }


        if(user.isBanned()){
            UserBan ban = user.getUserBan();
            req.getSession().setAttribute("banned", true);
            req.getSession().setAttribute("reason", ban.getReason());
            req.getSession().setAttribute("endTime", Utils.secondsToDatetime(ban.getEndTime()));
            req.getSession().setAttribute("login_error_message", "user.banned");
            log.error(LogMessage.ERROR + "User is banned");
            resp.sendRedirect(WebPath.LOGIN);
            return;
        }

        log.info("User " + user.getId() + " logged in");
        req.getSession().setAttribute("user", user);

        log.debug(LogMessage.CONTROLLER_FINISHED);

        if(user.getStatus() == UserStatus.ADMIN){
            resp.sendRedirect(WebPath.ADMIN);
        }else{
            resp.sendRedirect(WebPath.HOME);
        }
    }
}
