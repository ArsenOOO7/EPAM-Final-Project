package com.arsen.epam.internet.shop.web.controller.category;

import com.arsen.epam.internet.shop.entity.Category;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.service.validation.bean.CategoryValidation;
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
 * Adding category controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/category/add")
public class AddCategoryController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(AddCategoryController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug(LogMessage.CONTROLLER_GET);
        req.getRequestDispatcher(Views.CATEGORY_ADD).forward(req, resp);
        log.debug(LogMessage.CONTROLLER_FINISHED);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_POST);

        String message = CategoryValidation.validateCategory(req);
        if(!message.isEmpty()){
            req.getSession().setAttribute("category_error", message);
            log.error("Error code: " + message);
            resp.sendRedirect(WebPath.CATEGORIES_ADD);
            return;
        }

        String identifier = req.getParameter("identifier");
        String localeUA = req.getParameter("locale_ua");
        String localeEN = req.getParameter("locale_en");

        Category category = new Category();
        category.setIdentifier(identifier);
        category.setLocaleUA(localeUA);
        category.setLocaleEN(localeEN);

        log.trace("Identifier: " + identifier);
        log.trace("Locale UA: " + localeUA);
        log.trace("Locale EN: " + localeEN);

        log.trace("Saving category to DB");
        log.info("Add a new category: " + category.getIdentifier());
        HeadRepository.getCategoryRepository().save(category);

        log.debug(LogMessage.CONTROLLER_FINISHED);
        resp.sendRedirect(WebPath.CATEGORIES);

    }
}
