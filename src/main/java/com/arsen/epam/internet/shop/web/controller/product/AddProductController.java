package com.arsen.epam.internet.shop.web.controller.product;

import com.arsen.epam.internet.shop.entity.Category;
import com.arsen.epam.internet.shop.entity.image.ImageLoader;
import com.arsen.epam.internet.shop.entity.product.Product;
import com.arsen.epam.internet.shop.entity.product.color.Color;
import com.arsen.epam.internet.shop.entity.product.size.SizeUnit;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.category.specification.CategoryAllSpecification;
import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.service.utils.Utils;
import com.arsen.epam.internet.shop.service.validation.bean.ProductValidation;
import com.arsen.epam.internet.shop.web.path.Views;
import com.arsen.epam.internet.shop.web.path.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Add new product controller
 *
 * @author Arsen Sydoryk
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
@WebServlet("/product/add")
public class AddProductController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(AddProductController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_GET);
        log.trace("Getting list of categories...");
        List<Category> categories = HeadRepository.getCategoryRepository().findAll(new CategoryAllSpecification());

        log.trace("Setting list of categories to RequestScope...");
        req.setAttribute("categories", categories);
        log.trace("Setting list of colors to RequestScope...");
        req.setAttribute("colors", Color.values());
        log.trace("Setting list of size units to RequestScope...");
        req.setAttribute("units", SizeUnit.values());

        log.debug(LogMessage.CONTROLLER_FINISHED);
        req.getRequestDispatcher(Views.PRODUCT_ADD).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_POST);
        String error = ProductValidation.validateProduct(req);
        if(!error.isEmpty()){
            req.getSession().setAttribute("product_add_error", error);
            log.error("Error code: " + error);
            resp.sendRedirect(WebPath.PRODUCT_ADD);
            return;
        }

        String shortName = req.getParameter("shortname");
        String fullName = req.getParameter("fullname");
        String description = req.getParameter("description");

        String colorValue = req.getParameter("color");
        String sizeUnitValue = req.getParameter("size_unit");

        log.trace("Short name: " + shortName);
        log.trace("Short full name: " + fullName);
        log.trace("Short description: " + description);
        log.trace("Color: " + colorValue);
        log.trace("Size unit: " + sizeUnitValue);

        Product product = new Product();
        product.setShortName(shortName);
        product.setFullName(fullName);
        product.setDescription(description);

        int amount = Integer.parseInt(req.getParameter("amount"));
        double price = Double.parseDouble(req.getParameter("price"));
        int size = Integer.parseInt(req.getParameter("size"));
        int categoryId = Utils.getCategoryId(req.getParameter("category"));

        log.trace("Amount: " + amount);
        product.setAmount(amount);
        log.trace("Price: " + price);
        product.setPrice(price);

        product.setColor(Color.getInstance(colorValue));
        product.setSizeUnit(SizeUnit.getValue(sizeUnitValue));

        log.trace("Size: " + size);
        product.setSizeValue(size);

        log.trace("Category id: " + categoryId);
        product.setCategoryId(categoryId);

        if(req.getPart("preview_image").getSize() > 0){
            ImageLoader.createImage(req.getPart("preview_image"), product);
        }

        log.trace("Saving product to DB");
        HeadRepository.getProductRepository().save(product);

        log.debug(LogMessage.CONTROLLER_FINISHED);
        resp.sendRedirect(WebPath.PRODUCT_SEARCH);
    }
}
