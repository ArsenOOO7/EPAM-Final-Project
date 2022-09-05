package com.arsen.epam.internet.shop.web.controller.cart;

import com.arsen.epam.internet.shop.entity.cart.Cart;
import com.arsen.epam.internet.shop.entity.cart.status.PurchaseStatus;
import com.arsen.epam.internet.shop.entity.product.Product;
import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.cart.specification.CartIdSpecification;
import com.arsen.epam.internet.shop.repository.product.IProductRepository;
import com.arsen.epam.internet.shop.repository.product.specification.ProductIdSpecification;
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
 * Buy product from cart controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/cart/buy/*")
public class BuyProductCartController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(BuyProductCartController.class);

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
            req.getSession().setAttribute("cart_error_message", "buy.cart.product.buy.error.cart");
            log.error("Error code: buy.cart.product.buy.error.cart");
            resp.sendRedirect(WebPath.CART_CARTED);
            return;
        }

        log.debug(LogMessage.USER_SESSION);
        User user = (User) req.getSession().getAttribute("user");
        if(!user.hasEnoughMoney(cart.getPrice())){
            req.getSession().setAttribute("cart_error_message", "buy.cart.product.buy.error.money");
            log.error("Error code: buy.cart.product.buy.error.money");
            resp.sendRedirect(WebPath.CART_CARTED);
            return;
        }

        log.trace("Decreasing user balance on " + cart.getPrice());
        user.decreaseBalance(cart.getPrice());

        log.trace("Set cart status - purchased");
        cart.setStatus(PurchaseStatus.PURCHASED);
        log.trace("Updating cart in DB");
        HeadRepository.getCartRepository().update(cart);

        log.trace("Decrease amount of product");
        IProductRepository repository = HeadRepository.getProductRepository();
        Product product = repository.findOne(new ProductIdSpecification(cart.getProductId()));
        product.setAmount(product.getAmount() - cart.getAmount());

        log.trace("Updating product in DB");
        repository.update(product);

        log.trace("Success message code: buy.cart.product.success");
        req.getSession().setAttribute("cart_error_message", "buy.cart.product.success");

        log.debug(LogMessage.CONTROLLER_FINISHED);
        resp.sendRedirect(WebPath.CART_PURCHASED);

    }
}
