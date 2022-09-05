package com.arsen.epam.internet.shop.web.controller.user;

import com.arsen.epam.internet.shop.entity.image.ImageLoader;
import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.service.validation.bean.UserValidation;
import com.arsen.epam.internet.shop.web.path.Views;
import com.arsen.epam.internet.shop.web.path.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Date;


/**
 * User edit controller
 *
 * @author Arsen Sydoryk
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
@WebServlet("/user/edit")
public class UserEditController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(UserEditController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug(LogMessage.CONTROLLER_GET);
        req.getRequestDispatcher(Views.USER_EDIT).forward(req, resp);
        log.debug(LogMessage.CONTROLLER_FINISHED);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_POST);
        String message = UserValidation.validateUserEdit(req);
        if(!message.isEmpty()){
            req.getSession().setAttribute("edit_error_message", message);
            log.error("Error code: " + message);
            resp.sendRedirect(WebPath.USER_EDIT);
            return;
        }

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        String birthDate = req.getParameter("birth_date");

        log.trace("New user's name: " + name);
        log.trace("New user's surname: " + surname);
        log.trace("New user's birthdate: " + birthDate);

        log.debug(LogMessage.USER_SESSION);
        User user = (User) req.getSession().getAttribute("user");
        user.setName(name);
        user.setSurname(surname);
        user.setBirthDate(Date.valueOf(birthDate));

        if(req.getPart("avatar").getSize() > 0){
            ImageLoader.updateImage(req.getPart("avatar"), user);
        }

        HeadRepository.getUserRepository().update(user);
        log.debug(LogMessage.CONTROLLER_FINISHED);
        resp.sendRedirect(WebPath.USER_PROFILE);
    }
}
