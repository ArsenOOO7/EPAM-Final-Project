package com.arsen.epam.internet.shop.web.controller.admin.users.cart;


import com.arsen.epam.internet.shop.entity.cart.Cart;
import com.arsen.epam.internet.shop.entity.cart.status.PurchaseStatus;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.cart.specification.CartIdSpecification;
import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.service.utils.Utils;
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
 * Admin user's specific cart edit controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/admin/users/cart/edit/*")
public class AdminCartEditController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(AdminCartEditController.class);

    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        log.debug(LogMessage.CONTROLLER_GET);
        log.trace(LogMessage.EXTRACTING_ID);

        int id = IdValidator.getPathId(req);
        if (id <= 0) {
            log.error("Error: invalid id (" + id + ")");
            resp.sendRedirect(WebPath.ADMIN_USERS);
            return;
        }

        log.trace("Getting cart by id...");
        Cart cart = HeadRepository.getCartRepository().findOne(new CartIdSpecification(id));

        if(cart == null){
            log.error(LogMessage.ERROR + "undefined cart by id (" + id + ")");
            resp.sendRedirect(WebPath.ADMIN_USERS);
            return;
        }

        log.trace("Setting cart to RequestScope");
        req.setAttribute("purchase", cart);
        log.trace("Setting statuses to RequestScope");
        req.setAttribute("statuses", PurchaseStatus.values());

        log.debug(LogMessage.CONTROLLER_FINISHED);
        req.getRequestDispatcher(Views.ADMIN_CART_SHOW).forward(req, resp);

    }*/

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_GET);
        log.trace(LogMessage.EXTRACTING_ID);

        int id = IdValidator.getPathId(req);
        if (id <= 0) {
            log.error("Error: invalid id (" + id + ")");
            resp.sendRedirect(WebPath.ADMIN_USERS);
            return;
        }

        log.trace("Getting cart by id...");
        Cart cart = HeadRepository.getCartRepository().findOne(new CartIdSpecification(id));

        if(cart == null){
            log.error(LogMessage.ERROR + "undefined cart by id (" + id + ")");
            resp.sendRedirect(WebPath.ADMIN_USERS);
            return;
        }

        PurchaseStatus purchaseStatus = PurchaseStatus.getValue(Utils.getInt(req.getParameter("status")));
        if(purchaseStatus == PurchaseStatus.UNDEFINED){
            log.error(LogMessage.ERROR + "undefined status");
            resp.sendRedirect(WebPath.ADMIN_USERS);
            return;
        }

        log.trace("Change status to " + purchaseStatus.getIdentifier());
        cart.setStatus(purchaseStatus);
        log.trace("Updating cart");
        HeadRepository.getCartRepository().update(cart);

        log.debug(LogMessage.CONTROLLER_FINISHED);

        String redirect = "";
        switch(purchaseStatus.getId()){
            case 1 -> redirect = WebPath.ADMIN_USER_CART_LIST_CARTED;
            case 2 -> redirect = WebPath.ADMIN_USER_CART_LIST_PURCHASED;
            case 3 -> redirect = WebPath.ADMIN_USER_CART_LIST_CANCELLED;
        }

        resp.sendRedirect(redirect + cart.getUserId());
    }
}
