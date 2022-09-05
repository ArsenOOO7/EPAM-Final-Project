package com.arsen.epam.internet.shop.web.controller.auth;

import com.arsen.epam.internet.shop.entity.image.ImageLoader;
import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.user.IUserRepository;
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
 * User register controller
 *
 * @author Arsen Sydoryk
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(RegisterController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug(LogMessage.CONTROLLER_GET);
        req.getRequestDispatcher(Views.REGISTER_VIEW).forward(req, resp);
        log.debug(LogMessage.CONTROLLER_FINISHED);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_POST);

        String message = UserValidation.validateRegister(req);
        if(!message.isEmpty()){
            req.getSession().setAttribute("register_error_message", message);
            log.error(LogMessage.ERROR_CODE + message);
            resp.sendRedirect(WebPath.REGISTER);
            return;
        }

        IUserRepository repository = HeadRepository.getUserRepository();

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String birthDate = req.getParameter("birth_date");

        User finalUser = new User();
        finalUser.setName(name);
        finalUser.setSurname(surname);
        finalUser.setEmail(email);

        finalUser.setLogin(login);
        finalUser.setPassword(password);

        finalUser.setBalance(0);
        finalUser.setBirthDate(Date.valueOf(birthDate));
        finalUser.setStatus(0);

        log.trace("Name: " + name);
        log.trace("Surname: " + surname);
        log.trace("Email: " + email);
        log.trace("Email: " + email);
        log.trace("Login: " + login);
        log.trace("Birthdate: " + birthDate);

        if(req.getPart("avatar").getSize() > 0){
            ImageLoader.createImage(req.getPart("avatar"), finalUser);
        }

        log.trace("Save user to DB");
        repository.save(finalUser);

        log.info("User " + finalUser.getId() + " registered");
        req.getSession().setAttribute("user", finalUser);

        log.debug(LogMessage.CONTROLLER_FINISHED);
        resp.sendRedirect(WebPath.HOME);
    }
}
