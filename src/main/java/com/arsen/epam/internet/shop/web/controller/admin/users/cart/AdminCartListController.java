package com.arsen.epam.internet.shop.web.controller.admin.users.cart;


import com.arsen.epam.internet.shop.database.DBConnection;
import com.arsen.epam.internet.shop.entity.cart.Cart;
import com.arsen.epam.internet.shop.entity.cart.status.PurchaseStatus;
import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.cart.specification.CartUserSpecification;
import com.arsen.epam.internet.shop.repository.user.specification.UserIdSpecification;
import com.arsen.epam.internet.shop.service.data.Data;
import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.service.utils.Utils;
import com.arsen.epam.internet.shop.service.validation.id.IdValidator;
import com.arsen.epam.internet.shop.web.path.Views;
import com.arsen.epam.internet.shop.web.path.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Admin user's registered carts show controller
 *
 * @author Arsen Sydoryk
 */
public abstract class AdminCartListController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(AdminCartListController.class);

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

        log.trace(LogMessage.EXTRACTING_PAGE);

        String page = req.getParameter("page");
        int pageNumber = 1;

        if(page != null && !page.isEmpty()){
            pageNumber = Utils.getInt(page);
        }

        int userId = user.getId();

        log.trace("Getting cart object by id...");
        CartUserSpecification specification = getSpecification(userId);
        specification.setPage(pageNumber);

        log.trace("Setting user carts on specific page to RequestScope");
        List<Cart> list = HeadRepository.getCartRepository().findAll(specification);
        req.setAttribute("carts", list);
        log.trace("Setting user id to RequestScope");
        req.setAttribute("id", userId);
        log.trace("Setting current page to RequestScope");
        req.setAttribute("page", pageNumber);
        log.trace("Setting type (" + getType() +") of cart to RequestScope");
        req.setAttribute("type", getType());
        log.trace("Setting statuses to RequestScope");
        req.setAttribute("statuses", PurchaseStatus.values());

        log.trace("Getting and setting number of all pages by this query into RequestScope");
        specification.setSelect("COUNT(*)");
        req.setAttribute("pages", (int) Math.ceil(DBConnection.getInstance().count(specification)
                / (double) Data.MAX_ENTITIES_PAGE));

        log.debug(LogMessage.CONTROLLER_FINISHED);
        req.getRequestDispatcher(Views.ADMIN_CART_LIST).forward(req, resp);

    }


    protected abstract CartUserSpecification getSpecification(int userId);
    protected abstract String getType();

}
