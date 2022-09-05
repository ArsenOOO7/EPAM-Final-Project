package com.arsen.epam.internet.shop.web.controller.cart;

import com.arsen.epam.internet.shop.entity.cart.Cart;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.cart.specification.CartIdSpecification;
import com.arsen.epam.internet.shop.service.log.LogMessage;
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

/**
 * Show specific cart controller
 *
 * @author Arsen Sydoryk
 */
//@WebServlet("/cart/show/*")
public class ShowCartController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ShowCartController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_GET);
        log.trace(LogMessage.EXTRACTING_ID);

        int id = IdValidator.getPathId(req);
        if (id <= 0) {
            log.error(LogMessage.ERROR + "invalid id (" + id + ")");
            resp.sendRedirect(WebPath.CART);
            return;
        }

        log.trace("Getting cart by id...");
        Cart cart = HeadRepository.getCartRepository().findOne(new CartIdSpecification(id));

        if(cart == null){
            log.error(LogMessage.ERROR + "undefined cart by id (" + id + ")");
            resp.sendRedirect(WebPath.CART);
            return;
        }

        log.trace("Setting cart to RequestScope...");
        req.setAttribute("purchase", cart);

        log.debug(LogMessage.CONTROLLER_FINISHED);
        req.getRequestDispatcher(Views.CART_SHOW).forward(req, resp);
    }
}
