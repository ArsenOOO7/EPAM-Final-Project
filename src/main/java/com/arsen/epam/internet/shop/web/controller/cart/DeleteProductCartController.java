package com.arsen.epam.internet.shop.web.controller.cart;

import com.arsen.epam.internet.shop.entity.cart.Cart;
import com.arsen.epam.internet.shop.entity.cart.status.PurchaseStatus;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.cart.specification.CartIdSpecification;
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
 * Deleting cart controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/cart/delete/*")
public class DeleteProductCartController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(DeleteProductCartController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_GET);
        log.trace(LogMessage.EXTRACTING_ID);

        int id = IdValidator.getPathId(req);
        if (id <= 0) {
            log.error("Error: invalid id (" + id + ")");
            resp.sendRedirect(WebPath.CART_CARTED);
            return;
        }

        log.trace("Getting cart by id...");
        Cart cart = HeadRepository.getCartRepository().findOne(new CartIdSpecification(id));

        if(cart == null){
            log.error("Error: undefined cart by id (" + id + ")");
            resp.sendRedirect(WebPath.CART_CARTED);
            return;
        }

        if(cart.getStatus() != PurchaseStatus.CART){
            req.getSession().setAttribute("cart_error_message", "delete.cart.product.error.purchased");
            log.error("Error code: delete.cart.product.error.purchased");
            resp.sendRedirect(WebPath.CART_CARTED);
            return;
        }

        log.trace("Deleting cart...");
        HeadRepository.getCartRepository().delete(cart);

        log.debug(LogMessage.CONTROLLER_FINISHED);
        resp.sendRedirect(WebPath.CART_CARTED);

    }

}
