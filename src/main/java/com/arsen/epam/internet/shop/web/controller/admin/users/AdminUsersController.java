package com.arsen.epam.internet.shop.web.controller.admin.users;

import com.arsen.epam.internet.shop.database.DBManager;
import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.user.specification.UserAllSpecification;
import com.arsen.epam.internet.shop.service.data.Data;
import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.service.utils.Utils;
import com.arsen.epam.internet.shop.web.path.Views;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Admin users show controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/admin/users")
public class AdminUsersController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(AdminUsersController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_GET);

        log.trace(LogMessage.EXTRACTING_PAGE);
        String page = req.getParameter("page");
        int pageNumber = 1;

        if(page != null && !page.isEmpty()){
            pageNumber = Utils.getInt(page);
        }

        log.trace("Getting list of users on specific page...");
        UserAllSpecification specification = new UserAllSpecification(pageNumber);
        log.trace("Adding name parameter for searching");
        specification.setName(req.getParameter("name"));

        if(req.getParameter("banned") != null){
           log.trace("Adding banned parator for searching");
           specification.setBanned(true);
        }

        log.trace("Getting list of users...");
        List<User> users = HeadRepository.getUserRepository().findAll(specification);
        log.trace("Building query line");
        String queryLine = Utils.buildQueryLine(req);

        log.trace("Setting query line to RequestScope");
        req.setAttribute("query", queryLine);
        log.trace("Setting list of users to RequestScope");
        req.setAttribute("users", users);
        log.trace("Setting current page to RequestScope");
        req.setAttribute("page", pageNumber);

        log.trace("Getting and setting number of all pages by this query into RequestScope");
        specification.setCountAll();
        req.setAttribute("pages", (int) Math.ceil(DBManager.getInstance().count(specification)
                / (double) Data.MAX_ENTITIES_PAGE));

        log.debug(LogMessage.CONTROLLER_FINISHED);
        req.getRequestDispatcher(Views.ADMIN_USERS).forward(req, resp);

    }
}
