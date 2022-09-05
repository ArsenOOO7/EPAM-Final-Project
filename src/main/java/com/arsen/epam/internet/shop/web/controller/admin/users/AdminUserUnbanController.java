package com.arsen.epam.internet.shop.web.controller.admin.users;

import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.user.specification.UserIdSpecification;
import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.service.validation.id.IdValidator;
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
 * Admin user unban controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/admin/users/unban/*")
public class AdminUserUnbanController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(AdminUserUnbanController.class);


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

        log.trace("Getting user by id");
        User user = HeadRepository.getUserRepository().findOne(new UserIdSpecification(id));

        if(user == null){
            log.error(LogMessage.ERROR + "undefined user");
            resp.sendRedirect(WebPath.ADMIN_USERS);
            return;
        }

        if(!user.isBanned()){
            log.error(LogMessage.ERROR + "user is not banned");
            resp.sendRedirect(WebPath.ADMIN_USERS);
            return;
        }

        log.trace("Removing ban from user");
        HeadRepository.getBanRepository().delete(user.getUserBan().getId());

        log.debug(LogMessage.CONTROLLER_FINISHED);
        resp.sendRedirect(WebPath.ADMIN_USER_SHOW + user.getId());

    }
}
