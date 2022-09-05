package com.arsen.epam.internet.shop.web.controller.admin.users;

import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.user.specification.UserIdSpecification;
import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.service.validation.id.IdValidator;
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
 * Admin user show controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/admin/users/show/*")
public class AdminUserShowController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(AdminUserShowController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_GET);
        log.trace(LogMessage.EXTRACTING_ID);

        int id = IdValidator.getPathId(req);
        if (id <= 0) {
            log.error("Error: invalid id (" + id + ")");
            resp.sendRedirect(WebPath.ADMIN_USERS);
            return;
        }

        User user = HeadRepository.getUserRepository().findOne(new UserIdSpecification(id));

        if(user == null){
            log.error(LogMessage.ERROR + "undefined user");
            resp.sendRedirect(WebPath.ADMIN_USERS);
            return;
        }

        log.trace("Setting user to RequestScope...");
        req.setAttribute("user", user);

        log.debug(LogMessage.CONTROLLER_FINISHED);
        req.getRequestDispatcher(Views.ADMIN_USER_SHOW).forward(req, resp);

    }
}
