package com.arsen.epam.internet.shop.web.controller.cart;

import com.arsen.epam.internet.shop.entity.cart.Cart;
import com.arsen.epam.internet.shop.entity.cart.status.PurchaseStatus;
import com.arsen.epam.internet.shop.entity.product.Product;
import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.product.specification.ProductIdSpecification;
import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.service.utils.Utils;
import com.arsen.epam.internet.shop.service.validation.bean.CartValidation;
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
 * Adding product to cart controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/cart/add/*")
public class AddProductCartController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(AddProductCartController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_POST);
        log.trace(LogMessage.EXTRACTING_ID);

        int id = IdValidator.getPathId(req);
        if (id <= 0) {
            log.error("Error: invalid id (" + id + ")");
            resp.sendRedirect(WebPath.PRODUCT_SEARCH);
            return;
        }

        log.trace("Getting product by id...");
        Product product = HeadRepository.getProductRepository().findOne(new ProductIdSpecification(id));
        if(product == null){
            log.error("Error: undefined product");
            resp.sendRedirect(WebPath.PRODUCT_SEARCH);
            return;
        }

        String message = CartValidation.addProductValidate(req);
        if(!message.isEmpty()){
            req.getSession().setAttribute("cart_product_error_message", message);
            log.error("Error code: " + message);
            resp.sendRedirect(WebPath.PRODUCT_SHOW + product.getId());
            return;
        }

        int amount = Utils.getInt(req.getParameter("amount"));
        if(amount > product.getAmount()){
            req.getSession().setAttribute("cart_product_error_message", "cart.product.add.invalid.amount.enough");
            log.error("Error code: cart.product.add.invalid.amount.enough");
            resp.sendRedirect(WebPath.PRODUCT_SHOW + product.getId());
            return;
        }

        log.debug(LogMessage.USER_SESSION);
        User user = (User) req.getSession().getAttribute("user");
        Cart cart = new Cart();
        cart.setUserId(user.getId());
        cart.setProductId(product.getId());
        cart.setAmount(amount);
        cart.setPrice(0);
        cart.setStatus(PurchaseStatus.CART);

        log.trace("User id: " + user.getId());
        log.trace("Product id: " + product.getId());
        log.trace("Amount: " + amount);
        log.trace("Price: will be calculated in DB");

        log.trace("Saving cart to DB");
        HeadRepository.getCartRepository().save(cart);

        log.debug(LogMessage.CONTROLLER_FINISHED);
        resp.sendRedirect(WebPath.CART_CARTED);

    }
}
