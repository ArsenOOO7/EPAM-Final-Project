package com.arsen.epam.internet.shop.web.controller.admin.users;

import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.entity.user.ban.UserBan;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.ban.IBanRepository;
import com.arsen.epam.internet.shop.repository.ban.specification.BanUserIdSpecification;
import com.arsen.epam.internet.shop.repository.user.specification.UserIdSpecification;
import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.service.utils.Utils;
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
 * Admin user ban controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/admin/users/ban/*")
public class AdminUserBanController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(AdminUserBanController.class);

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

        log.trace("Setting user id to RequestScope...");
        req.setAttribute("id", id);

        log.debug(LogMessage.CONTROLLER_FINISHED);
        req.getRequestDispatcher(Views.ADMIN_USER_BAN).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_POST);
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

        String reason = req.getParameter("reason");
        String endTime = req.getParameter("end_time");

        log.trace("Reason: " + reason);
        log.trace("End time (hours): " + endTime);

        IBanRepository repository =  HeadRepository.getBanRepository();
        UserBan userBan = repository.findOne(new BanUserIdSpecification(user.getId()));
        if(userBan == null){
            log.trace("User have not been banned. Creating new new bean");
            userBan = new UserBan();
        }

        userBan.setUserId(user.getId());
        userBan.setReason(reason);
        userBan.setEndTime(Utils.getInt(endTime));

        log.trace("Saving ban to DB...");
        if(userBan.getId() > 0){
            repository.update(userBan);
        }else{
            repository.save(userBan);
        }

        log.debug(LogMessage.CONTROLLER_FINISHED);
        resp.sendRedirect(WebPath.ADMIN_USER_SHOW + user.getId());

    }
}
