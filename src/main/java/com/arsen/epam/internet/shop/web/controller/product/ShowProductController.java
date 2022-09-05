package com.arsen.epam.internet.shop.web.controller.product;

import com.arsen.epam.internet.shop.entity.product.Product;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.product.specification.ProductIdSpecification;
import com.arsen.epam.internet.shop.service.log.LogMessage;
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
 * Show specific product controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/product/show/*")
public class ShowProductController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ShowProductController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug(LogMessage.CONTROLLER_GET);
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
            log.error(LogMessage.ERROR + "invalid product");
            resp.sendRedirect(WebPath.PRODUCT_SEARCH);
            return;
        }

        log.trace("Setting product to RequestScope");
        req.setAttribute("product", product);

        log.trace(LogMessage.CONTROLLER_FINISHED);
        req.getRequestDispatcher(Views.PRODUCT_SHOW).forward(req, resp);
    }
}
