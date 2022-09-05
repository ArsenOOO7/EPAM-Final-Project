package com.arsen.epam.internet.shop.web.controller.user;

import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.service.utils.Utils;
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
 * User top up controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/user/topup")
public class UserTopUpController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(UserTopUpController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug(LogMessage.CONTROLLER_GET);
        req.getRequestDispatcher(Views.USER_TOP_UP).forward(req, resp);
        log.debug(LogMessage.CONTROLLER_FINISHED);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_POST);
        String message = UserValidation.validateUserTopUp(req);
        if(!message.isEmpty()){
            req.getSession().setAttribute("top_up_message", message);
            log.error("Error code: " + message);
            resp.sendRedirect(WebPath.USER_TOP_UP);
            return;
        }

        int money = Utils.getInt(req.getParameter("money"));

        money = Math.abs(money);

        log.debug(LogMessage.USER_SESSION);
        User user = (User) req.getSession().getAttribute("user");

        log.trace("Increasing user balance on " + money + "...");
        user.increaseBalance(money);


        HeadRepository.getUserRepository().update(user);
        log.debug(LogMessage.CONTROLLER_FINISHED);
        resp.sendRedirect(WebPath.USER_PROFILE);
    }

}
