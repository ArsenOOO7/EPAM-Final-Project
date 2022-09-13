package com.arsen.epam.internet.shop.web.controller.cart.list;

import com.arsen.epam.internet.shop.database.DBManager;
import com.arsen.epam.internet.shop.entity.cart.Cart;
import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.cart.specification.CartUserSpecification;
import com.arsen.epam.internet.shop.service.data.Data;
import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.service.utils.Utils;
import com.arsen.epam.internet.shop.web.path.Views;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Specific type carts list controller
 * Implementations: AdminCartedListController, AdminPurchasedListController, AdminCancelledListController
 *
 * @author Arsen Sydoryk
 */
public abstract class CartListController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(CartListController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_GET);
        log.trace(LogMessage.EXTRACTING_PAGE);
        String page = req.getParameter("page");
        int pageNumber = 1;

        if(page != null && !page.isEmpty()){
            pageNumber = Utils.getInt(page);
        }

        log.debug(LogMessage.USER_SESSION);
        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getId();

        log.trace("Selecting user carts on specific page...");
        CartUserSpecification specification = getSpecification(userId);
        specification.setPage(pageNumber);

        List<Cart> list = HeadRepository.getCartRepository().findAll(specification);

        log.trace("Setting list of carts to RequestScope");
        req.setAttribute("carts", list);

        log.trace("Setting type (" + getType() +") of cart to RequestScope");
        req.setAttribute("type", getType());
        log.trace("Setting current page to RequestScope");
        req.setAttribute("page", pageNumber);

        log.trace("Getting and setting number of all pages by this query into RequestScope");
        specification.setSelect("COUNT(*)");
        req.setAttribute("pages", (int) Math.ceil(DBManager.getInstance().count(specification)
                / (double) Data.MAX_ENTITIES_PAGE));

        log.debug(LogMessage.CONTROLLER_FINISHED);
        req.getRequestDispatcher(Views.CART_LIST).forward(req, resp);

    }


    protected abstract CartUserSpecification getSpecification(int userId);
    protected abstract String getType();

}
